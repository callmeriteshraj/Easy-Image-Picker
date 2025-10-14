# Easy Image Picker



> Step 1. Add the Jitpack  repository in your build.gradle(app level)
```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

```



> Step 2. Add the dependency in your build.gradle(module level)

```
  dependencies {
                implementation 'com.github.callmeriteshraj:Easy-Image-Picker:1.0.1'
  }
```



> Step 3. Add this file frovide in AndroidManifest.xml

```
  <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths" />
        </provider>
```

> Step 4. Make a file with name provider_paths.xml and paste all code 

```
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <!-- Cache directory for temporary images -->
    <cache-path
        name="cache"
        path="." />

    <!-- App-specific files directory -->
    <files-path
        name="files"
        path="." />

    <!-- External storage app-specific directory -->
    <external-files-path
        name="external_files"
        path="." />
```

    <!-- External cache directory -->
    <external-cache-path
        name="external_cache"
        path="." />
</paths>

