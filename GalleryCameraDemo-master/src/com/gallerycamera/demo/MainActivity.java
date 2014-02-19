package com.gallerycamera.demo;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static int THUMBNAIL_SIZE = 300;
	private static final int YOUR_SELECT_PICTURE_REQUEST_CODE = 232;

	private Button button;
	private ImageView image;
	private Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		image = (ImageView) findViewById(R.id.activity_main_image);
		button = (Button) findViewById(R.id.activity_main_button);
		button.setOnClickListener(buttonListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (bmp != null && !bmp.isRecycled()) {
			bmp.recycle();
			bmp = null;
		}
	}

	private View.OnClickListener buttonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// Determine Uri of camera image to save.
			FileUtils.createDefaultFolder(MainActivity.this);
			//final File file = FileUtils.createFile(FileUtils.IMAGE_FILE);
			//outputFileUri = Uri.fromFile(file);
	
			// Camera.
			final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			//captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
	
			final Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			//galleryIntent.setType("image/*");
			// Filesystems
			// galleryIntent.setAction(Intent.ACTION_GET_CONTENT); // To allow file managers or any other app that are not gallery app.
	
			final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Image");
			// Add the camera options.
			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { captureIntent });
			startActivityForResult(chooserIntent, YOUR_SELECT_PICTURE_REQUEST_CODE);
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			//if (resultCode == Activity.RESULT_OK) {
				if (requestCode == YOUR_SELECT_PICTURE_REQUEST_CODE) {
						Bundle extras2 = data.getExtras();
						if (extras2 != null) {				
							Uri selectedImage = data.getData();
							if (selectedImage != null) {
								String[] filePathColumn = {MediaStore.Images.Media.DATA};
								Cursor cursor = getContentResolver().query(
														selectedImage, filePathColumn, null, null, null);
								cursor.moveToFirst();

								int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
								String filePath = cursor.getString(columnIndex);
								cursor.close();
								
								bmp = ImageUtils.getThumbnail(this,filePath, THUMBNAIL_SIZE);
								image.setImageBitmap(bmp);
								bmp = null;
							}
						}
					}
				//}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
