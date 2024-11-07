async function load() {
    let responseFromController = await fetch(`/tasks/allTasks?isCompleted=false`);
    let tasksObj = await responseFromController.json();
    let listTable = document.getElementById('taskList');
    listTable.innerHTML = '';

    tasksObj.forEach(task => {
        let row = document.createElement("tr");
        row.innerHTML = `<td><input type="button" value="Complete" onclick="completeTask(${task.id})"></td>
                         <td>${task.id}</td>
                         <td>${task.name}</td>
                         <td>${task.deadline}</td>`;
        listTable.appendChild(row);
    });
}

async function addTask(event) {
    event.preventDefault();

    let taskName = document.getElementById('taskName').value;
    let taskDeadlineDate = document.getElementById('taskDeadlineDate').value;
    let taskDeadlineTime = document.getElementById('taskDeadlineTime').value;
    let deadline = `${taskDeadlineDate}T${taskDeadlineTime}`;

    await fetch('/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: taskName, deadline: deadline })
    });

    document.getElementById('taskForm').reset();
    await load();
    closeModal();
}

function closeModal() {
    let modal = document.getElementById('taskModal');
    modal.style.display = 'none';
}

function openModal() {
    let modal = document.getElementById('taskModal');
    modal.style.display = 'flex';
}

async function completeTask(taskId) {
    await fetch(`/tasks/${taskId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ isCompleted: true })
    });

    await load();
}

document.addEventListener("DOMContentLoaded", async function () {
    document.getElementById('OpenButton').addEventListener('click', openModal);
    document.getElementById('close').addEventListener('click',closeModal);
    await load();
    document.getElementById('taskForm').addEventListener('submit', addTask);
});
