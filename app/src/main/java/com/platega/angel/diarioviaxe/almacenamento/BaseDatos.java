package com.platega.angel.diarioviaxe.almacenamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
	public static final String NOME_BD="diarioviaxe";
	public SQLiteDatabase sqlLiteDB;
	public final static int VERSION_BD=1;
	private final String CONSULTAR_LUGARES ="SELECT * FROM Lugares order by nome";
	private final String TABOA_AULAS="Lugares";
	private static String creartaboapersoas="CREATE TABLE Lugares("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"nome VARCHARD(30) NOT NULL,"+"descripcion VARCHAR (70)"+")";


	public BaseDatos(Context context, int versionBD) {
		super(context, NOME_BD, null, VERSION_BD);
		// TODO Auto-generated constructor stub
	}



	

	public long engadirlugar(Lugares lugar_engadir){
		ContentValues valores = new ContentValues();
		valores.put("nome",lugar_engadir.getNome());
		valores.put("descripcion", lugar_engadir.getDescripcion());
		long id = sqlLiteDB.insert("Lugares",null,valores);

		return id;

	}
	public ArrayList<Lugares> obterLugares() {
		ArrayList<Lugares> aulas_devolver = new ArrayList<Lugares>();

		Cursor datosConsulta = sqlLiteDB.rawQuery(CONSULTAR_LUGARES, null);
		if (datosConsulta.moveToFirst()) {
			Lugares lugares;
			while (!datosConsulta.isAfterLast()) {
				lugares = new Lugares(datosConsulta.getLong(0),
						datosConsulta.getString(1),datosConsulta.getString(2));
				aulas_devolver.add(lugares);
				datosConsulta.moveToNext();
			}
		}

		return aulas_devolver;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(creartaboapersoas);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS Lugares");
		onCreate(db);
		
	}
	 
}
