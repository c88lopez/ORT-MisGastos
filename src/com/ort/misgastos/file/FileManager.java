package com.ort.misgastos.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.ort.misgastos.db.CategoryDAO;
import com.ort.misgastos.entities.Category;
import com.ort.misgastos.entities.Report;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class FileManager {
	public static void importarCategorias(Context context) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(context.getAssets().open("categorias.txt")));

			// do reading, usually loop until end of file reading
			String mLine;
			CategoryDAO categoryDao = new CategoryDAO(context);
			while ((mLine = reader.readLine()) != null) {
				String[] vec = mLine.split(";");

				if (vec.length == 2) {
					Category categoria = new Category(Integer.parseInt(vec[0]), vec[1]);
					categoryDao.insert(categoria);
				}
			}
		} catch (IOException e) {
			// log the exception
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Log.e(Context.STORAGE_SERVICE, e.getMessage());
				}
			}
		}
	}

	public static void reportExport(Context context, List<Report> reports) {
		File file = new File(context.getExternalFilesDir("data"), "reporte.txt");

		Toast toast = Toast.makeText(context, "Exportando a " + file.getPath(), 1000);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();

		String lineSeparator = System.getProperty("line.separator");

		for (Report report : reports) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file, true);

				FileWriter fWriter;

				try {
					fWriter = new FileWriter(fos.getFD());
					fWriter.write(report.getCategoria().getName() + ";" + Float.toString(report.getSpend()) + ";"
							+ report.getPercent() + lineSeparator);

					fWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fos.getFD().sync();
					fos.close();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.e(Context.STORAGE_SERVICE, exception.getMessage());
			}
		}
	}

}
