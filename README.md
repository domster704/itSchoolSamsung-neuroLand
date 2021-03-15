# MyProject_NeuroLand
Для запуска файла new_tens.py нужно установить библиотеку tensorflow 2.3.0

При попытке заупска WebChooseActivity с <code>Process p = Runtime.getRuntime().exec(...</code> и нажатии кнопки "Получить" произойдёт ошибка, так как почему-то файл не найден 

```
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.example.myproject_neuroland, PID: 12115
    java.lang.IllegalStateException: Could not execute method for android:onClick
        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:414)
        at android.view.View.performClick(View.java:7448)
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992)
        at android.view.View.performClickInternal(View.java:7425)
        at android.view.View.access$3600(View.java:810)
        at android.view.View$PerformClick.run(View.java:28305)
        at android.os.Handler.handleCallback(Handler.java:938)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at android.os.Looper.loop(Looper.java:223)
        at android.app.ActivityThread.main(ActivityThread.java:7656)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
     Caused by: java.lang.reflect.InvocationTargetException
        at java.lang.reflect.Method.invoke(Native Method)
        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:409)
        at android.view.View.performClick(View.java:7448) 
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992) 
        at android.view.View.performClickInternal(View.java:7425) 
        at android.view.View.access$3600(View.java:810) 
        at android.view.View$PerformClick.run(View.java:28305) 
        at android.os.Handler.handleCallback(Handler.java:938) 
        at android.os.Handler.dispatchMessage(Handler.java:99) 
        at android.os.Looper.loop(Looper.java:223) 
        at android.app.ActivityThread.main(ActivityThread.java:7656) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947) 
     Caused by: java.io.IOException: Cannot run program "python": error=2, No such file or directory
        at java.lang.ProcessBuilder.start(ProcessBuilder.java:1050)
        at java.lang.Runtime.exec(Runtime.java:699)
        at java.lang.Runtime.exec(Runtime.java:529)
        at java.lang.Runtime.exec(Runtime.java:426)
        at ru.gisupov.neuroland.main_ui.WebChooseActivity.getDataFromLink(WebChooseActivity.java:67)
        at java.lang.reflect.Method.invoke(Native Method) 
        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:409) 
        at android.view.View.performClick(View.java:7448) 
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992) 
        at android.view.View.performClickInternal(View.java:7425) 
        at android.view.View.access$3600(View.java:810) 
        at android.view.View$PerformClick.run(View.java:28305) 
        at android.os.Handler.handleCallback(Handler.java:938) 
        at android.os.Handler.dispatchMessage(Handler.java:99) 
        at android.os.Looper.loop(Looper.java:223) 
        at android.app.ActivityThread.main(ActivityThread.java:7656) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947) 
     Caused by: java.io.IOException: error=2, No such file or directory
        at java.lang.UNIXProcess.forkAndExec(Native Method)
        at java.lang.UNIXProcess.<init>(UNIXProcess.java:133)
        at java.lang.ProcessImpl.start(ProcessImpl.java:141)
        at java.lang.ProcessBuilder.start(ProcessBuilder.java:1029)
```
        
        
        
        
Если же запустить сервер вручную (файл app/src/main/java/ru/gisupov/neuroland/main_ui/python_neuro/neuroland/server-flask.py), то выдаст ошибку
```
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.example.myproject_neuroland, PID: 12431
    java.lang.IllegalStateException: Could not execute method for android:onClick
        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:414)
        at android.view.View.performClick(View.java:7448)
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992)
        at android.view.View.performClickInternal(View.java:7425)
        at android.view.View.access$3600(View.java:810)
        at android.view.View$PerformClick.run(View.java:28305)
        at android.os.Handler.handleCallback(Handler.java:938)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at android.os.Looper.loop(Looper.java:223)
        at android.app.ActivityThread.main(ActivityThread.java:7656)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
     Caused by: java.lang.reflect.InvocationTargetException
        at java.lang.reflect.Method.invoke(Native Method)
        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:409)
        at android.view.View.performClick(View.java:7448) 
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992) 
        at android.view.View.performClickInternal(View.java:7425) 
        at android.view.View.access$3600(View.java:810) 
        at android.view.View$PerformClick.run(View.java:28305) 
        at android.os.Handler.handleCallback(Handler.java:938) 
        at android.os.Handler.dispatchMessage(Handler.java:99) 
        at android.os.Looper.loop(Looper.java:223) 
        at android.app.ActivityThread.main(ActivityThread.java:7656) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947) 
     Caused by: java.io.IOException: Cleartext HTTP traffic to 127.0.0.1 not permitted
        at com.android.okhttp.HttpHandler$CleartextURLFilter.checkURLPermitted(HttpHandler.java:127)
        at com.android.okhttp.internal.huc.HttpURLConnectionImpl.execute(HttpURLConnectionImpl.java:462)
        at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getResponse(HttpURLConnectionImpl.java:411)
        at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getInputStream(HttpURLConnectionImpl.java:248)
        at ru.gisupov.neuroland.main_ui.WebChooseActivity.getHTML(WebChooseActivity.java:35)
        at ru.gisupov.neuroland.main_ui.WebChooseActivity.getDataFromLink(WebChooseActivity.java:74)
```
