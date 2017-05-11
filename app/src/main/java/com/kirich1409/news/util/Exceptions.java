package com.kirich1409.news.util;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * @author Kirill Rozov
 */

public final class Exceptions {

    public static UnsupportedOperationException newUnsupportedOperation(
            @NonNull String messageFormat, @NonNull Object... args) {
        return new UnsupportedOperationException(formatErrorMessage(messageFormat, args));
    }

    public static UnsupportedOperationException newUnsupportedOperation(
            @NonNull Exception cause, @NonNull String messageFormat, @NonNull Object... args) {
        return new UnsupportedOperationException(formatErrorMessage(messageFormat, args), cause);
    }

    public static IllegalArgumentException newIllegalArgument(
            @NonNull String messageFormat, @NonNull Object... args) {
        return new IllegalArgumentException(formatErrorMessage(messageFormat, args));
    }

    public static IllegalArgumentException newIllegalArgument(
            @NonNull Exception cause, @NonNull String messageFormat, @NonNull Object... args) {
        return new IllegalArgumentException(formatErrorMessage(messageFormat, args), cause);
    }

    @NonNull
    private static String formatErrorMessage(
            @NonNull String messageFormat, @NonNull Object[] args) {
        return String.format(Locale.ENGLISH, messageFormat, args);
    }

    private Exceptions() {
    }
}
