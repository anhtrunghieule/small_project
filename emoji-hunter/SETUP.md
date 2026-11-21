# Setup Instructions

This project requires **Java 17 or newer**. Confirm your version by running:

```
java -version
```

> 💡 Get the project first: download and unzip the starter kit into an
> `emoji-hunter` directory at the root of your repository — see
> [Getting the starter kit](README.md#getting-the-starter-kit) in the README.

---

## 1 · JUnit jar

Make sure this file exists in `lib/junit/`:

```
junit-platform-console-standalone-6.0.3.jar
```

It is included in the starter kit, so it should already be there. That is the only external dependency — Swing is bundled with Java.

---

## 2 · Install `make`

The project includes a `Makefile` with commands to make it easier to compile, run, test, and generate documentation. `make` is available by default on Linux and Mac but requires a one-time install on Windows.

**Mac**
```
xcode-select --install
```

**Linux**
`make` is pre-installed on most distributions. If not:
```
sudo apt install make      # Debian / Ubuntu
sudo dnf install make      # Fedora
```

**Windows** — choose one option:

- **Git Bash** (recommended for beginners): install [Git for Windows](https://gitforwindows.org/). `make` is included. Run all `make` commands inside the Git Bash terminal.
- **Chocolatey**: open PowerShell as Administrator and run `choco install make`.
- **WSL**: install the Windows Subsystem for Linux and work inside a Linux terminal.


---

## 3 · Open the project

Open the `emoji-hunter` folder in your editor:

- **VS Code** — install the **Extension Pack for Java**, then `File → Open Folder`
  and choose `emoji-hunter`.
- **Zed** — install the **Java** extension, then `File → Open` and choose
  `emoji-hunter`.

You compile, run, and test from a **terminal** using the `Makefile` in the project
root — this works the same in any editor:

| Command | What it does |
| ------- | ------------ |
| `make test` | compile and run the JUnit test suite |
| `make run` | compile and launch the game |
| `make compile` | compile only |
| `make doc` | generate the Javadoc |
| `make clean` | remove compiled output |

> 💡 Open an in-editor terminal (VS Code and Zed both use `` Ctrl+` ``) and run
> these from the `emoji-hunter` project root.

---

## 4 · Verify the setup

Run the following from the project root to confirm everything is working:

```
make test
```

All 50 tests will fail at this stage — that is expected. If the command runs without a compilation error, your setup is correct.
