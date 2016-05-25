package com.exametric_administration.tools;

/**
 * Global variables for the application usable everywhere.
 */
public class GlobalVariables {
    // Base URL change it if your Web Service hasn't this IP
    public static String BASE_URL = "http://192.168.1.14:8080/exametrics-ws";

    // Areas static URI
    public static String AREAS_URI = "/areas";
    public static String UPLOAD_AREA_URI = "/areas";

    // Notes static URI
    public static String BY_ID_NOTES_URI = "/notes?id=";
    public static String UPLOAD_NOTE_URI = "/notes";

    // Points static URI
    public static String UPLOAD_POINTS_URI = "/points";
    public static String POINTS_URI = "/points";

    // Static AreaID, usable when you return to the DetailsNote parent. Prevent some crash issues when you return to the NoteListView
    public static int ACTUAL_AREA = 0;
}
