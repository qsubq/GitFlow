package com.example.javacoretraining.training.classes;

import java.util.Scanner;

public class ClassesFourth {
        private int hh, mm, ss;
        public ClassesFourth(int hh_, int mm_, int ss_) {
            set_hh(hh_);
            set_mm(mm_);
            set_ss(ss_);
        }
        public ClassesFourth(int hh_, int mm_) {
            this(hh_, mm_, 0);
        }
        public ClassesFourth() {
            this(0, 0, 0);
        }
        public void set_hh(int hh_) {
            if (hh_ < 0 || hh_ > 23)
                hh = 0;
            else
                hh = hh_;
        }
        public void set_mm(int mm_) {
            if (mm_ < 0 || mm_ > 59)
                mm = 0;
            else
                mm = mm_;
        }
        public void set_ss(int ss_) {
            if (ss_ < 0 || ss_ > 59)
                ss = 0;
            else
                ss = ss_;
        }
        public int hours() {
            return hh;
        }
        public int minutes() {
            return mm;
        }
        public int seconds() {
            return ss;
        }
        public void print() {
            System.out.println(toString());
        }
        public String toString() {
            return String.format("%02d:%02d:%02d", hh, mm, ss);
        }

        static ClassesFourth read(Scanner scanner) {
            ClassesFourth time = new ClassesFourth();
            System.out.println("hh: ");
            time.set_hh(scanner.nextInt());

            System.out.println("mm: ");
            time.set_mm(scanner.nextInt());

            System.out.println("ss: ");
            time.set_ss(scanner.nextInt());

            return time;
        }
        public void add_seconds(int ss_) {
            ss += ss_;
            mm += ss / 60;
            ss = ss % 60;
            hh += mm / 60;
            mm = mm % 60;
            hh = hh % 24;
        }
        public void add_minutes(int mm_) {
            add_seconds(mm_ * 60);
        }
        public void add_hours(int hh_) {
            add_seconds(hh_ * 60 * 60);
        }
}
