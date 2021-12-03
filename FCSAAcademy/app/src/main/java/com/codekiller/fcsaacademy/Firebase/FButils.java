package com.codekiller.fcsaacademy.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FButils {
    StorageReference eBookStorage, adminStorage, photoStorage;
    DatabaseReference userDatabase, queryDatabase, favoriteDatabase, eBookDatabase, adminDatabase,
                        photosDatabase, eventDatabase;

    public FButils() {
        eBookStorage = FirebaseStorage.getInstance().getReference("eBooks");
        adminStorage = FirebaseStorage.getInstance().getReference("Teachers photos");
        photoStorage = FirebaseStorage.getInstance().getReference("Photos");

        userDatabase = FirebaseDatabase.getInstance().getReference("Candidates");
        queryDatabase = FirebaseDatabase.getInstance().getReference("Query");
        favoriteDatabase = FirebaseDatabase.getInstance().getReference("Favorite");
        eBookDatabase = FirebaseDatabase.getInstance().getReference("eBooks");
        adminDatabase = FirebaseDatabase.getInstance().getReference("Admin");
        photosDatabase = FirebaseDatabase.getInstance().getReference("Photos");
        eventDatabase = FirebaseDatabase.getInstance().getReference("Events");
    }

    public StorageReference getAdminStorage() {
        return adminStorage;
    }

    public StorageReference getPhotoStorage() {
        return photoStorage;
    }

    public DatabaseReference getPhotosDatabase() {
        return photosDatabase;
    }

    public DatabaseReference getEventDatabase() {
        return eventDatabase;
    }

    public DatabaseReference getAdminDatabase() {
        return adminDatabase;
    }
    public DatabaseReference getQueryDB(){
        return queryDatabase;
    }
    public DatabaseReference getUserDB(){
        return userDatabase;
    }
    public StorageReference getStorage(){
        return eBookStorage;
    }
    public DatabaseReference getFavoriteDB(){
        return favoriteDatabase;
    }
    public DatabaseReference getEBookDB(){
        return eBookDatabase;
    }
}
