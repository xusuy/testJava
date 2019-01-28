package com.java8.Streams;

/**
 * @author xsy
 * @create 2018-11-20 13:32
 * @desc：Streams api
 **/
public class Task {
        private final StatusEnum status;
        private final Integer points;

        Task( final StatusEnum status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public StatusEnum getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
}

