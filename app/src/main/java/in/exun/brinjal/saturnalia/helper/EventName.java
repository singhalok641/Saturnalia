package in.exun.brinjal.saturnalia.helper;

import android.database.Cursor;

public class EventName {

    String Title, Thumb, Event_Type,header_image;
    int id;

    public EventName(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex("id"));
        Title = cursor.getString(cursor.getColumnIndex("Event_Name"));
        Thumb = cursor.getString(cursor.getColumnIndex("thumb"));
        Event_Type = cursor.getString(cursor.getColumnIndex("Event_Type"));
        header_image = cursor.getString(cursor.getColumnIndex("header_image"));
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String Event_Type) {
        this.Title = Title;
    }

    public String getTitle() {
        return Title;
    }

    public void setThumb(String Event_Type) {
        this.Thumb = Thumb;
    }

    public String getThumb() {
        return Thumb;
    }


    public void setEvent_Type(String Event_Type) {
        this.Event_Type = Event_Type;
    }


    public String getEvent_Type() {return Event_Type;}

    public void setHeader_image(String Event_Type) {
        this.header_image = header_image;
    }


    public String getHeader_image() {return header_image;}
}

