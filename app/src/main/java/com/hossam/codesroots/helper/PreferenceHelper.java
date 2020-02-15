package com.hossam.codesroots.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hossam.codesroots.delivery24.R;


public class PreferenceHelper {

	private static SharedPreferences app_prefs;
	private static String app_ref = "userdetails";
	private static String photo = "photo";
	private static String Token = "token";
	private static String UserId = "userid";
	private static String deliveryId = "delivery";
	private static String Language = "App_Language";
	private final static String CURRENTLAT = "latitude";
	private final static String CURRENTLONG = "longtude";
	private final String CURRENTCATEGRY = "CURRENTCATEGRY";
	private static final String USER_GROUP_ID = "USERGROUPID";
	private final static String USER_NAME = "USERNAME";
	private final static String ROOM_ID = "ROOMID";
	private static String OrderId = "OrderId";
	private final String OrderDetails = "OrderDetails";
	private final String DeliveryLocation = "DeliveryLocation";
	private final String DurationRequest = "DurationRequest";
	private final String ImageUri = "ImageUri";
	private final String FirstTime = "FirstTime";
	private final String UserID = "UserID";
	private final String AccessToken = "AccessToken";
	private final String Email = "Email";
	private final String FirstName = "FirstName";
	private final String LastName = "LastName";
	private final String Phone = "Phone";
	private final String Gender = "Gender";
	private final String Password = "Password";
	private final String language = "App_Language";
	private final String SocketId = "SocketId";

	private static Context context;


	public PreferenceHelper(Context context) {
		this.context = context;
		try {
			app_prefs = context.getSharedPreferences(app_ref,
					Context.MODE_PRIVATE);
		}
		catch (NullPointerException e)
		{
		}
	}

	public static  void setUSER_GROUP_ID(int group) {
		Editor edit = app_prefs.edit();
		edit.putInt(USER_GROUP_ID, group);
		edit.apply();
	}

	public static void setUserName(String name) {
		Editor edit = app_prefs.edit();
		edit.putString(USER_NAME, name);
		edit.apply();
	}

	public static void setOrderId(int order_id) {
		Editor edit = app_prefs.edit();
		edit.putInt(OrderId, order_id);
		edit.apply();
	}


	public static void setRoomId(String room_id) {
		Editor edit = app_prefs.edit();
		edit.putString(ROOM_ID, room_id);
		edit.apply();
	}

	public static int getOrderId() {
		return app_prefs.getInt(OrderId, 0);
	}

	public static String getRoomId() {
		return app_prefs.getString(ROOM_ID,null);
	}

	public static String getUserName() {
		return app_prefs.getString(USER_NAME, context.getString(R.string.mandob));
	}


	public static int getUSER_GROUP_ID()  {
		return app_prefs.getInt(USER_GROUP_ID, 0);
	}

	public static void setCURRENTLAT(String currentlat) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLAT, currentlat);
		edit.apply();
	}

	public static void setCURRENTLONG(String currentlong) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLONG, currentlong);
		edit.apply();
	}

	public  void setCURRENTCATEGRY(String currentcategry) {

		Editor edit = app_prefs.edit();
		edit.putString(CURRENTCATEGRY, currentcategry);
		edit.apply();
	}

	public String getCURRENTCATEGRY() {
		return app_prefs.getString(CURRENTCATEGRY, null);
	}

	public static String getCURRENTLAT() {
		if (app_prefs!=null)
		return app_prefs.getString(CURRENTLAT, null);
		else return null;
	}

	public static String getCURRENTLONG()  {
		if (app_prefs!=null)
		return app_prefs.getString(CURRENTLONG, "0");
		else return null;
	}

	public static String getToken() {
		return app_prefs.getString(Token,null);
	}

	public static void setToken(String API_TOKEN) {
		Editor edit = app_prefs.edit();
		edit.putString(Token, API_TOKEN);
		edit.commit();
	}

	public static int getUserId() {
		if (app_prefs!=null)
		return app_prefs.getInt(UserId,237);
		else
			return 0;
	}

	public static  String getdeliveryId() {
		if (app_prefs!=null)
			return app_prefs.getString(deliveryId,"1");
		else
			return "0";
	}
	public void setdeliveryId(String ID) {
		Editor edit = app_prefs.edit();
		edit.putString(deliveryId, ID);
		edit.apply();
	}

	public void setLanguage(String language) {
		Editor edit = app_prefs.edit();
		edit.putString(Language, language);
		edit.commit();
	}

	public String getLanguage() {
		return app_prefs.getString(Language,null);
	}

	public String getphoto() {
		return app_prefs.getString(photo,null);
	}

	public void setUserId(int user_id) {
		Editor edit = app_prefs.edit();
		edit.putInt(UserId, user_id);
		edit.apply();
	}


	public void setphoto(String uri){
	Editor edit = app_prefs.edit();
	edit.putString(photo, uri);
	edit.apply();
}



	public void clearRequestData() {

 	}

	public String getsokcetId() {
		return app_prefs.getString(SocketId, null);
	}

	public void setsokcetId(String sokcetid) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(SocketId, sokcetid);
		edit.apply();
	}
	public String getlanguage() {
		return app_prefs.getString("App_Language", null);
	}
	public static String getCurrentLanguage(Context context) {
		String langPref = context.getPackageName() + "App_Language";
		SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
		String old = prefs.getString(langPref, "ar");
		return old;
	}
	public void setlanguage(String API_language) {
		String langPref = context.getPackageName() + "App_Language";
		SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(langPref, API_language);
		editor.apply();
//		SharedPreferences.Editor edit = app_prefs.edit();
//		edit.putString(language, API_language);
//		edit.apply();
	}
	public String getGender() {
		return app_prefs.getString(Gender, null);
	}

	public void setGender(String API_Gender) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(Gender, API_Gender);
		edit.apply();
	}

	public String getPassword() {
		return app_prefs.getString(Password, null);
	}

	public void setPassword(String API_Password) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(Password, API_Password);
		edit.apply();
	}

	public String getPhone() {
		return app_prefs.getString(Phone, null);
	}

	public void setPhone(String API_Phone) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(Phone, API_Phone);
		edit.apply();
	}

	public String getLastName() {
		return app_prefs.getString(LastName, null);
	}

	public void setLastName(String API_LastName) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(LastName, API_LastName);
		edit.apply();
	}

	public String getFirstName() {
		return app_prefs.getString(FirstName, null);
	}

	public void setFirstName(String API_FirstName) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(FirstName, API_FirstName);
		edit.apply();
	}

	public String getEmail() {
		return app_prefs.getString(Email, null);
	}

	public void setEmail(String API_Email) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(Email, API_Email);
		edit.apply();
	}

	public String getFirstTime() {
		return app_prefs.getString(FirstTime, null);
	}

	public void setFirstTime(String API_FirstTime) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(FirstTime, API_FirstTime);
		edit.apply();
	}

	public String getUserID() {
		return app_prefs.getString(UserID, null);
	}

	public void setUserID(String API_UserID) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(UserID, API_UserID);
		edit.apply();
	}

	public String getAccessToken() {
		return app_prefs.getString(AccessToken, null);
	}

	public void setAccessToken(String API_AccessToken) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(AccessToken, API_AccessToken);
		edit.apply();
	}

	public String getOrderDetails() {
		return app_prefs.getString(OrderDetails, null);
	}

	public void setOrderDetails(String API_OrderDetails) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(OrderDetails, API_OrderDetails);
		edit.apply();
	}

	public String getDeliveryLocation() {
		return app_prefs.getString(DeliveryLocation, null);
	}

	public void setDeliveryLocation(String API_DeliveryLocation) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(DeliveryLocation, API_DeliveryLocation);
		edit.apply();
	}


	public String getDurationRequest() {
		return app_prefs.getString(DurationRequest, null);
	}

	public void setDurationRequest(String API_DurationRequest) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(DurationRequest, API_DurationRequest);
		edit.apply();
	}

	public String getImageUri() {
		return app_prefs.getString(ImageUri, null);
	}

	public void setImageUri(String API_ImageUri) {
		SharedPreferences.Editor edit = app_prefs.edit();
		edit.putString(ImageUri, API_ImageUri);
		edit.apply();
	}

	public void ClearOrder() {
		setOrderDetails(null);
		setDeliveryLocation(null);
		setImageUri(null);
		setDurationRequest(null);
	}

	public void Logout(){
		setOrderDetails(null);
		setDeliveryLocation(null);
		setImageUri(null);
		setDurationRequest(null);
		setAccessToken(null);
		setFirstName(null);
		setLastName(null);
		setUserID(null);
		setGender(null);
		setPassword(null);
		setEmail(null);
		setPhone(null);
	}
}
