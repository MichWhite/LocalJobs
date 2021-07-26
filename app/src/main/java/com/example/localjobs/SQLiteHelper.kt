package com.example.localjobs

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Worker.db"
        private const val TBL_Worker = "tbl_Worker"
        private const val ID = "id"
        private const val Name = "Name"
        private const val Email = "Email"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableWorker = ("CREATE TABLE "+ TBL_Worker+"("+
                ID+" Integer Primary Key, "+ Name + " TEXT, "+ Email + " TEXT " +")")
        db?.execSQL(createTableWorker)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_Worker")
    }



    fun insertWorker(workerModel: WorkerModel):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, workerModel.id)
        contentValues.put(Name, workerModel.name)
        contentValues.put(Email, workerModel.email)

        val success = db.insert(TBL_Worker, null, contentValues)
        db.close()
        return success
    }


    fun getAllWorkers(): ArrayList<WorkerModel>{
        val wrkList: ArrayList<WorkerModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_Worker"
        val db = this.readableDatabase

        val cursor : Cursor?
        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            db.close();
            return ArrayList()

        }
        var id: Int
        var name: String
        var email : String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                email = cursor.getString(cursor.getColumnIndex("Email"))

                val wrk = WorkerModel(id = id, name=name, email = email)
                wrkList.add(wrk)

            }while(cursor.moveToLast())
        }
            return wrkList
        }

    }




