package com.seotm.coloringview.draws.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.seotm.coloringview.draws.ViewSize;

/**
 * Created by seotm on 09.06.17.
 */

class SetupImageBounds {

    private final DrawImageImpl drawImage;

    SetupImageBounds(DrawImageImpl drawImage) {
        this.drawImage = drawImage;
    }

    void setupImageBounds(@NonNull ViewSize viewSize) {
        if (!setupBoundFromDrawable(viewSize)) {
            setupBoundsFromBitmap(viewSize);
        }
    }

    private boolean setupBoundFromDrawable(@NonNull ViewSize viewSize) {
        if (drawImage.drawableImg == null) return false;
        if (!viewSize.isEstablished()) {
            drawImage.drawableImg.setBounds(0, 0, 0, 0);
            return true;
        }
        drawImage.bounds = ImageBoundsCreator.create(drawImage.drawableImg, viewSize.getWidth(), viewSize.getHeight());
        DrawableToBitmap drawableToBitmap = new DrawableToBitmap(drawImage.drawableImg);
        drawImage.bitmapImg = drawableToBitmap.convertToBitmap(drawImage.bounds);
        return true;
    }

    private void setupBoundsFromBitmap(@NonNull ViewSize viewSize) {
        if (drawImage.bitmapImg == null || !viewSize.isEstablished()) return;
        drawImage.bounds = ImageBoundsCreator.create(drawImage.bitmapImg, viewSize.getWidth(), viewSize.getHeight());
        Bitmap bitmap = Bitmap.createScaledBitmap(drawImage.bitmapImg, drawImage.bounds.width(), drawImage.bounds.height(), false);
        drawImage.bitmapImg.recycle();
        drawImage.bitmapImg = bitmap;
    }

}
