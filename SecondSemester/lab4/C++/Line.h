#ifndef LINE_H
#define LINE_H

#include <iostream>

class Line {
    private:
        char *_line{};
        size_t size;
        void init(char *);
    public:
        Line();
        explicit Line(char *);
        Line(const Line &);
        int getLength();
        char* getLine();

        Line operator=(const Line &);
        Line operator+(const Line &);
        Line operator-(char);

        ~Line();
};


#endif