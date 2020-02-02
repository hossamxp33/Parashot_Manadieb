package com.hossam.codesroots.presentation.confirm_payment;

import android.Manifest;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hossam.codesroots.entities.ConfirmpaymentData;
import com.hossam.codesroots.helper.FileUtils;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.MainActivity;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ConfirmPaymentActivity extends AppCompatActivity {

    private static final int LOAD_IMG_REQUEST_CODE =5000 ;
    int bankId;
    ImageView imageView;
    TextView title,save;
    private ConfirmPaymentViewModel mViewModel;
    EditText username,phone,clientBank;
    MultipartBody.Part photo_part ;
    ConfirmpaymentData confirmpaymentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        initialize();
        mViewModel.AddPaymentmutableLiveData.observe(this,aBoolean ->
                {
                    if (aBoolean)
                    {
                        save.setText(R.string.send);
                        save.setEnabled(true);
                        Toast.makeText(this,getText(R.string.addinfosuccess),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else
                    {
                        save.setText(R.string.send);
                        save.setEnabled(true);
                        Toast.makeText(this,getText(R.string.error_add),Toast.LENGTH_SHORT).show();

                    }
                }
                );
        mViewModel.errorAddpayment.observe(this,throwable ->
                {
                    save.setText(R.string.send);
                    save.setEnabled(true);
                    if (throwable!=null)
                     Toast.makeText(this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this,getText(R.string.error_add),Toast.LENGTH_SHORT).show();

                });

    }

    private void initialize() {
        imageView = findViewById(R.id.img);
        bankId = getIntent().getIntExtra("bankId",0);
        title = findViewById(R.id.title);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        clientBank = findViewById(R.id.client_bank);
        save = findViewById(R.id.send);
        title.setText(getText(R.string.paymentinfo));
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ConfirmPaymentViewModel.class);
    }

    public void uploadImage(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, LOAD_IMG_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_IMG_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final Uri imageUri = data.getData();
                imageView.setImageURI(imageUri);
                imageView.setVisibility(View.VISIBLE);
                photo_part = prepareFilePart("iban_image", imageUri);
            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sendInfo(View view) {
        if (validate()) {
            save.setText("...");
            save.setEnabled(false);
            confirmpaymentData = new ConfirmpaymentData(bankId, clientBank.getText().toString(),
                    username.getText().toString(), phone.getText().toString(), photo_part);
            mViewModel.AddPayment(confirmpaymentData);
        }
    }

    private boolean validate() {

        if (username.getText().toString().matches(""))
        {
            username.setError(getText(R.string.addusername));
            return false;
        }
        else if (phone.getText().toString().matches(""))
        {
            phone.setError(getText(R.string.addphone));
            return false;
        }
        else if (clientBank.getText().toString().matches(""))
        {
            clientBank.setError(getText(R.string.addbank));
            return false;
        }
        else if (photo_part==null)
        {
            Snackbar.make(save,getText(R.string.addpayimg),Snackbar.LENGTH_SHORT).show();
            return false;
        }
           else
               return true;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new ViewModelFactory(this.getApplication());
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(this, fileUri);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image"),
                        file
                );
        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }

}
