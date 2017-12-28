//
// Created by miodrag.milosevic on 12/28/2017.
//
#include "color.h"

int Color::red(int color) {
    return (color >> 16) & 0xFF;
}

int Color::green(int color) {
    return (color >> 8) & 0xFF;
}

int Color::blue(int color) {
    return color & 0xFF;
}

int Color::argb(int alpha, int red, int green, int blue) {
    return  (alpha << 24) | (red << 16) | (green << 8) | blue;
}

int Color::rgb(int red, int green, int blue) {
    return 0xff000000 | (red << 16) | (green << 8) | blue;
}

int Color::alpha(int color) {
    return color >> 24;
}
