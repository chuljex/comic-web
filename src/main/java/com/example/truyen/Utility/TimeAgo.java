package com.example.truyen.Utility;

import java.time.*;
import java.util.Objects;

public class TimeAgo {

    public static String calculateTimeAgo(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return "Không xác định";
        }

        // Múi giờ Hà Nội
        ZoneId hanoiZone = ZoneId.of("Asia/Ho_Chi_Minh");

        // Chuyển thời gian đầu vào sang múi giờ Hà Nội
        ZonedDateTime dateTimeInHanoi = dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(hanoiZone);

        // Lấy thời gian hiện tại ở Hà Nội
        ZonedDateTime nowInHanoi = ZonedDateTime.now(hanoiZone);

        // Kiểm tra nếu thời gian đầu vào lớn hơn hiện tại
        if (dateTimeInHanoi.isAfter(nowInHanoi)) {
            return "Trong tương lai";
        }

        // Tính khoảng cách thời gian
        Duration duration = Duration.between(dateTimeInHanoi, nowInHanoi);

        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " giây trước";
        }

        long minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + " phút trước";
        }

        long hours = minutes / 60;
        if (hours < 24) {
            return hours + " giờ trước";
        }

        long days = hours / 24;
        if (days < 7) {
            return days + " ngày trước";
        }

        long weeks = days / 7;
        if (weeks < 4) {
            return weeks + " tuần trước";
        }

        long months = days / 30; // Trung bình mỗi tháng có 30 ngày
        if (months < 12) {
            return months + " tháng trước";
        }

        long years = days / 365; // Trung bình mỗi năm có 365 ngày
        return years + " năm trước";
    }
}