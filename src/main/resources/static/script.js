async function load() {
        let responseFromController = await fetch(`/tasks/allTasks?isCompleted=false`);

        let tasksObj = await responseFromController.json();
        let listTable =  document.getElementById(`taskList`);
        listTable.innerHTML = ``;

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

        let response = await fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: taskName,
                deadline: deadline
            })
        });

        document.getElementById('taskForm').reset();
        load();
}

async function completeTask(taskId) {
    let response = await fetch(`/tasks/${taskId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ isCompleted: true })
    });
    load();
}

async function loadCompletedTasks() {
    let responseFromController = await fetch(`/tasks/completedTasks`);

    let tasksObj = await responseFromController.json();
    let historyTableBody = document.querySelector('.history-table tbody');
    historyTableBody.innerHTML = ``;

    tasksObj.forEach(task => {
        let row = document.createElement("tr");
        row.innerHTML = `<td>${task.name}</td>
                         <td>${task.deadline}</td>
                         <td>Completed</td>`;
        historyTableBody.appendChild(row);
    });
}

document.addEventListener("DOMContentLoaded", loadCompletedTasks);
document.getElementById('taskForm').addEventListener('submit', addTask);
document.addEventListener("DOMContentLoaded", load);
