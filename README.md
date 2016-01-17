# Android Webview Test

Este es un proyecto de prueba para ver como poder realizar una aplicacion que contenga un WebView. La idea es poder montar una webapp sobre una app de android para poder servir localmente todo el contenido estatico pero mantener las request por ajax al servidor.

## Crear Projecto

El proyecto se creo con el siguiente comando:

```
$ android create project --target 3 --name WebviewTestApp --path ./android-web-view-test --activity MainActivity --package ar.com.nckweb.webviewtest
```

Para obtener el id del target se uso el comando `android list target`:

```
$ android list target
Available Android targets:
----------
id: 1 or "android-15"
     Name: Android 4.0.3
     Type: Platform
     API level: 15
     Revision: 3
     Skins: WSVGA, WVGA800 (default), WXGA800, WQVGA400, WVGA854, WQVGA432, WXGA720, HVGA, QVGA
     ABIs : armeabi-v7a, x86
----------
id: 2 or "Google Inc.:Google APIs:15"
     Name: Google APIs
     Type: Add-On
     Vendor: Google Inc.
     Revision: 2
     Description: Android + Google APIs
     Based on Android 4.0.3 (API level 15)
     Libraries:
      * com.android.future.usb.accessory (usb.jar)
          API for USB Accessories
      * com.google.android.media.effects (effects.jar)
          Collection of video effects
      * com.google.android.maps (maps.jar)
          API for Google Maps
     Skins: WXGA800, WSVGA, WXGA720, WQVGA400, QVGA, WVGA800 (default), HVGA, WVGA854, WQVGA432
     ABIs : armeabi-v7a
----------
id: 3 or "android-17"
     Name: Android 4.2
     Type: Platform
     API level: 17
     Revision: 1
     Skins: WSVGA, WVGA800 (default), WXGA800, WQVGA400, WVGA854, WXGA800-7in, WQVGA432, WXGA720, HVGA, QVGA
     ABIs : armeabi-v7a, mips
----------
id: 4 or "Google Inc.:Google APIs:17"
     Name: Google APIs
     Type: Add-On
     Vendor: Google Inc.
     Revision: 1
     Description: Android + Google APIs
     Based on Android 4.2 (API level 17)
     Libraries:
      * com.android.future.usb.accessory (usb.jar)
          API for USB Accessories
      * com.google.android.media.effects (effects.jar)
          Collection of video effects
      * com.google.android.maps (maps.jar)
          API for Google Maps
     Skins: WXGA800, WXGA800-7in, WSVGA, WXGA720, WQVGA400, QVGA, WVGA800 (default), HVGA, WVGA854, WQVGA432
     ABIs : armeabi-v7a
```


Se uso el id `3`, es decir un minimo de Android 4.2 (API level 17). Tambien se podria haber usado `android-17` en vez de la id para especificar dicho target

## Correr proyecto 


```
$ android update project -p . --target android-17 --subprojects
$ ant debug
$ adb devices
$ adb install ./bin/MainActivity-debug.apk
$ adb shell am start -n ar.com.nckweb.webviewtest/ar.com.nckweb.webviewtest.MainActivity

```

### Usar un dispositivo fisico

Para utilizar un dispositivo fisico, hay que primero conectarlo, en el dispositivo hay que activiar el modo desarrollador y el debuggin por usb.

Para listar los dispositivos conectados se puede usar el comando `adb devices -l`, si no aparece el dispositivo conectado, chquear que se hayan activado el modo desarrollador y el debuggin por usb!.

Para instalar el apk en el device fisico se deben usar los comandos mencionados anteriormente pero especificando el flag `-d` con el id del device.


### Desinstalar app


```
$ adb uninstall ar.com.nckweb.webviewtest
```
