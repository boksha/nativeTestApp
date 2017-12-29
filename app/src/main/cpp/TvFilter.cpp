//
// Created by miodrag.milosevic on 12/29/2017.
//

#include "TvFilter.h"
#include "color.h"


void TvFilter::transformPixels(jint *pixelArray, jint imageWidth, jint imageHeight) {

    jint R, G, B, A;
    for (jint x = 0; x < imageWidth; x++) {
        for (jint y = 0; y < imageHeight; y += GAP) {
            A = R = G = B = 0;

            for (jint w = 0; w < GAP; w++) {
                jint index = (y + w) * imageWidth + x;
                if (index < imageWidth * imageHeight) {
//median value
                    A = Color::alpha(pixelArray[index]);
                    R += Color::red(pixelArray[index]);
                    G += Color::green(pixelArray[index]);
                    B += Color::blue(pixelArray[index]);

                }
            }
            for (jint w = 0; w < GAP; w++) {
                jint index = (y + w) * imageWidth + x;
                if (index < imageWidth * imageHeight) {
                    if (w == 0) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, R / GAP, 0, 0);
                    }
                    if (w == 1) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, 0, G / GAP, 0);
                    }
                    if (w == 2) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, 0, 0, B / GAP);
                    }
//					if (w == 3) {
//						pixels[(y + w) * width + x] =Color.argb(A,255, 255, 255);
//					}
                }
            }
        }
    }
}
