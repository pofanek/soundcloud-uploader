# Jak zrobić logikę wyboru plików MP3 (Jetpack Compose)

`Mp3UploadBox` to czysty UI. Cała logika wyboru i obsługi plików żyje w ekranie, który go używa, a pole dostaje tylko `onClick`.

```kotlin
Mp3UploadBox(
    enabled = true,
    onClick = { /* tu odpalisz picker */ }
)
```

---

## 1. Launcher do wyboru plików

W ekranie, w którym używasz `Mp3UploadBox`, zarejestruj `rememberLauncherForActivityResult(...)` z jednym z kontraktów:

- `ActivityResultContracts.OpenMultipleDocuments()` – wiele plików, wynik to `List<Uri>`
- `ActivityResultContracts.OpenDocument()` – jeden plik, wynik to `Uri?`

Linki:

- Ogólnie o wyniku aktywności: https://developer.android.com/training/basics/intents/result
- Wersja dla Compose: https://developer.android.com/jetpack/compose/libraries#activity-result
- `OpenMultipleDocuments`: https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts.OpenMultipleDocuments
- `OpenDocument`: https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts.OpenDocument

## 2. Uruchomienie pickera

W `onClick` wołasz `launcher.launch(arrayOf("audio/mpeg"))`.

Tablica to filtr typów MIME. Dzięki niemu system pokazuje tylko pliki MP3.

Tło teorii (Storage Access Framework): https://developer.android.com/training/data-storage/shared/documents-files

## 3. Walidacja pliku

Filtr MIME nie daje gwarancji, bo niektóre aplikacje źle oznaczają pliki. Sprawdź dodatkowo:

- typ przez `contentResolver.getType(uri)`
- albo nazwę pliku (czy kończy się na `.mp3`)

Linki:

- `ContentResolver.getType`: https://developer.android.com/reference/android/content/ContentResolver#getType(android.net.Uri)
- Nazwa pliku z kolumny `OpenableColumns.DISPLAY_NAME`: https://developer.android.com/reference/android/provider/OpenableColumns

## 4. Trwały dostęp do pliku

Domyślnie `Uri` przestaje działać po restarcie aplikacji. Żeby zapisać go na później (np. w bazie), wywołaj:

```kotlin
contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
```

Link: https://developer.android.com/reference/android/content/ContentResolver#takePersistableUriPermission(android.net.Uri,%20int)

## 5. Odczyt pliku i metadanych

- Strumień do kopiowania lub uploadu: `contentResolver.openInputStream(uri)`
- Tytuł, wykonawca i długość utworu: `MediaMetadataRetriever`

Link: https://developer.android.com/reference/android/media/MediaMetadataRetriever

## 6. Stan w UI

Wybrane pliki trzymaj w `mutableStateListOf<Uri>()` albo w ViewModelu, żeby ekran odświeżał się po wyborze.

Link: https://developer.android.com/develop/ui/compose/state

---

## Sugerowana kolejność nauki

1. Kroki 1 i 2 – sprawdź, że picker otwiera się i zwraca `Uri`.
2. Krok 3 – dodaj walidację MP3.
3. Kroki 4 i 5 – tylko jeśli chcesz plik zapisywać, odtwarzać lub czytać metadane.
4. Krok 6 – podepnij wynik pod stan ekranu.
