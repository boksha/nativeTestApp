//
// Created by miodrag.milosevic on 12/28/2017.
//

#ifndef NATIVETEST_COLOR_H
#define NATIVETEST_COLOR_H
class Color{
public:
    static int alpha(int color);
    static int red(int color);
    static int green(int color);
    static int blue(int color);
    static int argb(int alpha, int red, int green, int blue);
    static int rgb(int red, int green, int blue);
};


#endif //NATIVETEST_COLOR_H
