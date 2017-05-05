package com.littlelan.ReverseTether;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public final class aa {
    Process f81a;
    BufferedReader f82b;
    DataOutputStream f83c;
    ReverseTetherActivity f84d;
    boolean f85e;

    aa(ReverseTetherActivity reverseTetherActivity, boolean z) {
        this.f84d = reverseTetherActivity;
        this.f85e = z;
    }

    public final String m93a(String str) {
        String str2 = "";
        try {
            if (this.f81a == null) {
                ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
                String[] strArr = new String[1];
                strArr[0] = this.f85e ? "su" : "sh";
                this.f81a = processBuilder.command(strArr).redirectErrorStream(true).start();
                this.f82b = new BufferedReader(new InputStreamReader(this.f81a.getInputStream()));
                this.f83c = new DataOutputStream(this.f81a.getOutputStream());
            }
            while (this.f82b.ready()) {
                if (this.f82b.read() == -1) {
                    break;
                }
            }
            synchronized (this.f83c) {
                this.f83c.writeBytes(new StringBuilder(String.valueOf(str)).append("\necho ").append(ae.f129Z).append("\n").toString());
                this.f83c.flush();
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
            while (true) {
                String readLine = this.f82b.readLine();
                if (readLine == null || readLine.equals(ae.f129Z)) {
                    return str2;
                }
                str2 = new StringBuilder(String.valueOf(str2)).append(readLine).append("\n").toString();
            }
        } catch (Throwable th) {
            return ae.ah;
        }
    }
}
