package com.gallerycamera.demo;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtils {

	/**
	 * Get a thumbnail bitmap.
	 * @param uri
	 * @return a thumbnail bitmap
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Bitmap getThumbnail(Context context, String filePath, int thumbnailSize) throws FileNotFoundException, IOException {
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither = true;// optional
		onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		BitmapFactory.decodeFile(filePath, onlyBoundsOptions);
		if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
			return null;

		int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

		double ratio = (originalSize > thumbnailSize) ? (originalSize / thumbnailSize) : 1.0;

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
		bitmapOptions.inDither = true;// optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		Bitmap bitmap = BitmapFactory.decodeFile(filePath, bitmapOptions);
		return bitmap;
	}

	/**
	 * Resolve the best value for inSampleSize attribute.
	 * @param ratio
	 * @return
	 */
	private static int getPowerOfTwoForSampleRatio(double ratio) {
		int k = Integer.highestOneBit((int) Math.floor(ratio));
		if (k == 0)
			return 1;
		else
			return k;
	}
	
}
