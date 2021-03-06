package com.sust.attendence.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sust.attendence.Manage.ManageActivity;
import com.sust.attendence.Others.ToastMessage;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Attendance_Database";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String CONSTRAINT = " NOT NULL UNIQUE";
    private static final String FOREIGN_KEY_CONSTRAINT = " FOREIGN KEY";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_ENTRIES_INSTRUCTOR = "CREATE TABLE "
            + Contract.Entry.INSTRUCTOR_TABLE_NAME + " (" + Contract.Entry._ID + " INTEGER PRIMARY KEY,"
            + Contract.Entry.INSTRUCTOR_COLUMN_NAME_1 + TEXT_TYPE + COMMA_SEP
            + Contract.Entry.INSTRUCTOR_COLUMN_NAME_2 + TEXT_TYPE + CONSTRAINT + COMMA_SEP
            + Contract.Entry.INSTRUCTOR_COLUMN_NAME_3 + TEXT_TYPE + " )";

    private static final String SQL_CREATE_ENTRIES_TITLE = "CREATE TABLE "
            + Contract.Entry_title.TITLE_TABLE_NAME + " (" + Contract.Entry_title.TITLE_COLUMN_NAME_1
            + INT_TYPE + COMMA_SEP + Contract.Entry_title.TITLE_COLUMN_NAME_2 + TEXT_TYPE + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + "(" + Contract.Entry_title.TITLE_COLUMN_NAME_1 + ")" + "REFERENCES "
            + Contract.Entry.INSTRUCTOR_TABLE_NAME + "(" + Contract.Entry._ID + ") ON DELETE CASCADE" + COMMA_SEP
            + "PRIMARY KEY ( " + Contract.Entry_title.TITLE_COLUMN_NAME_1 + COMMA_SEP
            + Contract.Entry_title.TITLE_COLUMN_NAME_2 + ")" + ")";



    private static final String SQL_CREATE_ENTRIES_STUDENT ="CREATE TABLE "
            + Contract.Entry_students.STUDENT_TABLE_NAME + " (" + Contract.Entry_students._ID + " INTEGER PRIMARY KEY " + COMMA_SEP
            + Contract.Entry_students.STUDENT_COLUMN_NAME_1 + INT_TYPE +" NOT NULL "+ COMMA_SEP
            + Contract.Entry_students.STUDENT_COLUMN_NAME_2 + INT_TYPE + COMMA_SEP
            + Contract.Entry_students.STUDENT_COLUMN_NAME_3 + TEXT_TYPE + COMMA_SEP
            + Contract.Entry_students.STUDENT_COLUMN_NAME_4 + TEXT_TYPE + "NOT NULL " + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + " ( " + Contract.Entry_students.STUDENT_COLUMN_NAME_2 + COMMA_SEP
            + Contract.Entry_students.STUDENT_COLUMN_NAME_3 + " ) " + " REFERENCES "
            + Contract.Entry_title.TITLE_TABLE_NAME + " ( " + Contract.Entry_title.TITLE_COLUMN_NAME_1 + COMMA_SEP
            + Contract.Entry_title.TITLE_COLUMN_NAME_2 + " ) ON DELETE CASCADE " + COMMA_SEP
            + " UNIQUE ( " + Contract.Entry_students.STUDENT_COLUMN_NAME_1 + COMMA_SEP + Contract.Entry_students.STUDENT_COLUMN_NAME_2
            + COMMA_SEP + Contract.Entry_students.STUDENT_COLUMN_NAME_3 + " ) " + " ) ";;


    private static final String SQL_CREATE_ENTRIES_ATTENDANCE_FREQUENCY = "CREATE TABLE "
            + Contract.Entry_attendance_frequency.TABLE_NAME + " (" + Contract.Entry_attendance_frequency._ID + " INTEGER PRIMARY KEY " + COMMA_SEP
            + Contract.Entry_attendance_frequency.COLUMN_NAME_1 + INT_TYPE + COMMA_SEP
            + Contract.Entry_attendance_frequency.COLUMN_NAME_2 + TEXT_TYPE + COMMA_SEP
            + Contract.Entry_attendance_frequency.COLUMN_NAME_3 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL" + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + " ( " + Contract.Entry_attendance_frequency.COLUMN_NAME_1 + COMMA_SEP +
            Contract.Entry_attendance_frequency.COLUMN_NAME_2 + " ) " + " REFERENCES "
            + Contract.Entry_title.TITLE_TABLE_NAME + " ( " + Contract.Entry_title.TITLE_COLUMN_NAME_1 + COMMA_SEP +
            Contract.Entry_title.TITLE_COLUMN_NAME_2 + " ) ON DELETE CASCADE "
            + " ) ";

    private static final String SQL_CREATE_ENTRIES_ABSENT_RECORD ="CREATE TABLE "
            + Contract.Entry_absent_record.TABLE_NAME + " (" + Contract.Entry_absent_record._ID + " INTEGER PRIMARY KEY " + COMMA_SEP
            + Contract.Entry_absent_record.COLUMN_NAME_1 + INT_TYPE + " NOT NULL " + COMMA_SEP
            + Contract.Entry_absent_record.COLUMN_NAME_2 + INT_TYPE  + " NOT NULL " + COMMA_SEP
            + Contract.Entry_absent_record.COLUMN_NAME_3 + TEXT_TYPE + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + " ( " + Contract.Entry_absent_record.COLUMN_NAME_1 +" ) "
            + " REFERENCES " + Contract.Entry_students.STUDENT_TABLE_NAME + " ( " + Contract.Entry_students._ID
            + " ) ON DELETE CASCADE " + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + " ( " + Contract.Entry_absent_record.COLUMN_NAME_2 +" ) "
            + " REFERENCES " + Contract.Entry_attendance_frequency.TABLE_NAME + " ( " + Contract.Entry_attendance_frequency._ID
            + " ) ON DELETE CASCADE " + COMMA_SEP
            + " UNIQUE ( " + Contract.Entry_absent_record.COLUMN_NAME_1 + COMMA_SEP
            + Contract.Entry_absent_record.COLUMN_NAME_2 + " ) " + " ) ";


    private static final String SQL_CREATE_ENTRIES_EXTRA_FIELD ="CREATE TABLE "
            + Contract.Entry_extra_field.TABLE_NAME + " (" + Contract.Entry_extra_field._ID + " INTEGER PRIMARY KEY " + COMMA_SEP
            + Contract.Entry_extra_field.COLUMN_NAME_1 + INT_TYPE +" NOT NULL "+ COMMA_SEP
            + Contract.Entry_extra_field.COLUMN_NAME_2 + TEXT_TYPE +" NOT NULL " +COMMA_SEP
            + Contract.Entry_extra_field.COLUMN_NAME_3 + TEXT_TYPE + COMMA_SEP
            + FOREIGN_KEY_CONSTRAINT + " ( " + Contract.Entry_extra_field.COLUMN_NAME_1 + " ) " + " REFERENCES "
            + Contract.Entry_students.STUDENT_TABLE_NAME + " ( " + Contract.Entry_students._ID + " ) ON DELETE CASCADE " + COMMA_SEP
            + " UNIQUE ( " + Contract.Entry_extra_field.COLUMN_NAME_1 + COMMA_SEP + Contract.Entry_extra_field.COLUMN_NAME_2
            + " ) " + " ) ";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

            db.execSQL(SQL_CREATE_ENTRIES_INSTRUCTOR);
            db.execSQL(SQL_CREATE_ENTRIES_TITLE);
            db.execSQL(SQL_CREATE_ENTRIES_STUDENT);
            db.execSQL(SQL_CREATE_ENTRIES_ATTENDANCE_FREQUENCY);
            db.execSQL(SQL_CREATE_ENTRIES_ABSENT_RECORD);
            db.execSQL(SQL_CREATE_ENTRIES_EXTRA_FIELD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
