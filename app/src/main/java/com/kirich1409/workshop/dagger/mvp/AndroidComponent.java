package com.kirich1409.workshop.dagger.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface AndroidComponent {

    void startActivity(@NonNull Intent intent);

    void startActivity(@NonNull Intent intent, @Nullable Bundle options);

    void startActivityForResult(@NonNull Intent intent, int requestCode);

    void startActivityForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options);

    @NonNull
    Context getContext();
}
