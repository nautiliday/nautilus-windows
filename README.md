# Nautilus Windows

Windows build of Nautilus

# Download

## Pre-Built Binaries

Pre-built binaries are not signed, which means your antivirus or smart-screen software may flag it as an
unwanted program. If there is a demand for it, I may start releasing them, but for now, building from source
is a viable option since your AV or Smart Screen filter wouldn't flag it.

## Building from source

### Pre-requisites

- JDK 21 or later
- Build Tool: Maven (optional, maven wrapper will be provided)

### Steps

- Clone the repository
```shell
git clone https://github.com/eggy03/Nautilus.git
cd Nautilus
```
- Build the application 
```shell
./mvnw -Pdist package jpackage:jpackage@win
```

The generated binaries will be available in `../Nautilus/target/output`

Note that each subsequent build requires manually clearing out the target folder or else the build will fail

- The build uses `jpackage` to generate native app images
- Output format depends on the target OS
- A minimal runtime is generated using `jlink` which is then packaged into the target build
- This makes your build portable

# Screenshots

### Windows

A modified build of Nautilus with hidden Hardware ID and Processor ID, running on Windows 11 24H2

<img width="850" height="640" alt="Screenshot 2026-04-07 122534" src="https://github.com/user-attachments/assets/79bcb6bf-ea05-4c85-9e2f-5b27a5d57208" />

# License

[GPLv3](/LICENSE.md)
