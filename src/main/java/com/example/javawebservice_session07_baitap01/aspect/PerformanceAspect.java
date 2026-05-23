package com.example.javawebservice_session07_baitap01.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    // Pointcut: Áp dụng cho mọi phương thức thuộc các class nằm trong package com.bank.digital.transaction.service
    @Around("execution(* com.example.javawebservice_session07_baitap01.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. Lấy thời gian trước khi hàm chạy
        long startTime = System.currentTimeMillis();

        // Lấy tên phương thức đang chạy để in log cho rõ ràng
        String methodName = joinPoint.getSignature().getName();

        try {
            // 2. Cho phép phương thức gốc (target method) thực thi và lấy kết quả trả về
            Object result = joinPoint.proceed();
            return result;
        } finally {
            // 3. Tính toán thời gian sau khi hàm chạy xong (kể cả khi có lỗi xảy ra nhờ đặt trong finally)
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // In log hiệu năng tập trung đúng yêu cầu nghiệp vụ
            System.out.println("LOG: " + methodName + " chạy trong " + executionTime + "ms");
        }
    }
}