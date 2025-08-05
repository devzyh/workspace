const {exec} = reqnode('child_process');

const batFile = 'D:/Storage/Markdown/GitSync.bat';

let working = false;

function gitSync(e) {
    if (working) {
        return;
    }

    if (e.type !== 'load' && e.key !== 'F12') {
        return
    }

    working = true;

    exec(batFile, (error, stdout, stderr) => {
        working = false;

        if (error) {
            confirm(error.message);
            return;
        }

        if (e.key !== 'F12') {
            return;
        }
        confirm(stdout);
    });
}

window.addEventListener('load', gitSync);
window.addEventListener('keydown', gitSync);
