/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import org.tensorflow.demo.Classifier.Recognition;

import java.util.List;

public class RecognitionScoreView extends View implements ResultsView {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint fgPricePaint; 
  private final Paint bgPaint;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setColor(0xffffffff);
    fgPaint.setTextSize(textSizePx);

    fgPricePaint = new Paint();
    fgPricePaint.setColor(0xff00ff00);
    fgPricePaint.setTextSize(textSizePx);
    
    bgPaint = new Paint();
    //    bgPaint.setColor(0xcc4285f4);
    // aarrggbb
    bgPaint.setColor(0x00000000);
  }

  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 200;
    final int xPrice = 900;
    int y = (int) (fgPaint.getTextSize() * 3.0f);

    canvas.drawPaint(bgPaint);

    if (results != null) {

        // display all results
	/*      for (final Recognition recog : results) {
        canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
        y += fgPaint.getTextSize() * 1.5f;
      }
	*/
      
      final Recognition recog = results.get(0);
      // assumes training on background as its own image
      if (!recog.getTitle().equals("background")) {
          // quick hack, didn't feel like parsing the prices from the labels or having
          //   a map of labels to prices
          if (recog.getTitle().equals("Luna Bar")) {
              canvas.drawText(recog.getTitle(), x, y, fgPaint);
              canvas.drawText("250 KES", xPrice, y, fgPricePaint);
          }
          if (recog.getTitle().equals("Dawn Soap")) {
              canvas.drawText(recog.getTitle(), x, y, fgPaint);
              canvas.drawText("399 KES", xPrice, y, fgPricePaint);
          }
          if (recog.getTitle().equals("Severed Head")) {
              canvas.drawText(recog.getTitle(), x-100, y, fgPaint);
              canvas.drawText("500,000 KES", xPrice-100, y, fgPricePaint);
          }
      }
    }
  }
}
