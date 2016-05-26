package com.exametric_administration.tools;

/**
 * Variables globales de l'application
 */
public class GlobalVariables {
    // URL de base du Web Services, à changer si vous n'avez pas la même IP
    public static String BASE_URL = "http://192.168.1.14:8080/exametrics-ws";

    // URI statique des Areas
    public static String AREAS_URI = "/areas";
    public static String UPLOAD_AREA_URI = "/areas";

    // URI statique des Notes
    public static String BY_ID_NOTES_URI = "/notes?id=";
    public static String UPLOAD_NOTE_URI = "/notes";

    // URI statique des Points
    public static String UPLOAD_POINTS_URI = "/points";
    public static String POINTS_URI = "/points";

    /**
     * AreaID statique, utilisable quand ont retourne au parent de DetailsNote. Évite certains crashes de l'application lors du retours
     * à NoteListView
     */
    public static int ACTUAL_AREA = 0;
}
