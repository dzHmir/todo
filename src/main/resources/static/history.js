async function loadCompletedTasks() {
    let responseFromController = await fetch(`/tasks/completedTasks`);
    let tasksObj = await responseFromController.json();
    let historyTableBody = document.querySelector('.history-table tbody');

    if (historyTableBody) {
        historyTableBody.innerHTML = '';
        tasksObj.forEach(task => {
            let row = document.createElement("tr");
            row.innerHTML = `<td>${task.name}</td>
                             <td>${task.deadline}</td>
                             <td>Completed</td>`;
            historyTableBody.appendChild(row);
        });
    }
}

document.addEventListener("DOMContentLoaded", loadCompletedTasks);

function showJumpScare() {
    const jumpScareImage = document.getElementById('jumpScareImage');
    jumpScareImage.style.display = 'block';
    jumpScareImage.style.opacity = 1;

    setTimeout(() => {
        jumpScareImage.style.opacity = 0;
        setTimeout(() => {
            jumpScareImage.style.display = 'none';
        }, 500);
    }, 20000);
}


document.getElementById('andriiCameoButton').addEventListener('click', showJumpScare);
