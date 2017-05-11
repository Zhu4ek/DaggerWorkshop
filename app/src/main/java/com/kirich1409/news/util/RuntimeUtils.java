package com.kirich1409.news.util;

import android.os.Build;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * @author kirylrozau
 */

public final class RuntimeUtils {

    public static int availableProcessors() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Runtime.getRuntime().availableProcessors();
        } else {
            return getNumCoresOldPhones();
        }
    }

    private RuntimeUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the number of cores available in this device, across all processors.
     * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
     *
     * @return The number of cores, or 1 if failed to get result
     */
    private static int getNumCoresOldPhones() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                return Pattern.matches("cpu[0-9]+", pathname.getName());
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Default to return 1 core
            return 1;
        }
    }
}
