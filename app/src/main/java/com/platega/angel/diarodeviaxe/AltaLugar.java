package com.platega.angel.diarodeviaxe;



import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.platega.angel.diariodeviaxe.utiles.Constantes;
import com.platega.angel.diariodeviaxe.utiles.Utiles;
import com.platega.angel.diarioviaxe.almacenamento.BaseDatos;
import com.platega.angel.diarioviaxe.almacenamento.Lugares;

public class AltaLugar extends Activity {

	BaseDatos basedatos;

	private void xestionarEventos(){
		
		ImageButton img = (ImageButton)findViewById(R.id.imgBtn_altalugar_volver);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		img = (ImageButton)findViewById(R.id.imgBtn_altalugar_novoLugar);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!comprobarNome()){
					return;
				}
				Utiles.amosarMensaxe(R.string.string_altalugar_mensaxe_altaFeita, AltaLugar.this);

				EditText nome = (EditText)findViewById(R.id.editT_altalugar_nome);
				EditText descrip = (EditText)findViewById(R.id.editT_altalugar_desciprcion);


				Lugares lugar = new Lugares(0,nome.getText().toString(), descrip.getText().toString());
				basedatos.engadirlugar(lugar);
				Toast.makeText(getApplicationContext(), nome.getText().toString() + " Añadiuse a base de datos", Toast.LENGTH_LONG).show();
				String datos="";
				nome.setText(datos);
				descrip.setText(datos);



				// Lanzamos a activity de AltaRecordos coa nova Id.
				Intent intento = new Intent(AltaLugar.this,AltaRecordos.class);
//				long id = lugar.getId();
//
//				intento.putExtra(Constantes.ID_LUGAR, id);
//				intento.putExtra(Constantes.NOME_LUGAR, lugar.getNome());
				
				startActivity(intento);
				
				finish();
			}
		});
		
	}

	
	private boolean comprobarNome(){
		EditText edit = (EditText)findViewById(R.id.editT_altalugar_nome);
		if (edit.getText().length()==0){
			Utiles.amosarMensaxe(R.string.string_altalugar_mensaxe_nomeObrigatorio, this);
			return false;
		}
		return true;
		
	}
	public void onStart(){
		super.onStart();
		if(basedatos==null){
			basedatos=new BaseDatos(this,1);
			basedatos.sqlLiteDB=basedatos.getWritableDatabase();
		}
	}
	public void onStop() {
		super.onStop();
		if (basedatos != null) {
			basedatos.close();
			basedatos = null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_altalugar);
		
		xestionarEventos();

	}
}
