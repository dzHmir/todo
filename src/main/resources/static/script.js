async function load() {
        let responseFromController = await fetch(`/tasks/allTasks?isCompleted=false`);
        if(!responseFromController.ok) {
            throw new Error('Failed to fetch tasks');
        }

        let tasksObj = await responseFromController.json();
        const listTable =  document.getElementById(`taskList`);
        listTable.innerHTML = ``;

        tasksObj.forEach(task => {
            let row = document.createElement("tr");

            row.innerHTML = `<td><input type="button" value="Complite" onclick="completeTask(${task.id})"></td>
                         <td>${task.id}</td>
                         <td>${task.name}</td>
                         <td>${task.deadline}</td>`;

            listTable.appendChild(row);
        });
}

async function addTask(event) {
    event.preventDefault();

    const taskName = document.getElementById('taskName').value;
    const taskDeadlineDate = document.getElementById('taskDeadlineDate').value;
    const taskDeadlineTime = document.getElementById('taskDeadlineTime').value;

    const deadline = `${taskDeadlineDate}T${taskDeadlineTime}`;

        const response = await fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: taskName,
                deadline: deadline
            })
        });

        if (!response.ok) {
            throw new Error('Failed to create task');
        }
        document.getElementById('taskForm').reset();
        load();
}

async function completeTask(taskId) {
    const response = await fetch(`/tasks/${taskId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ isCompleted: true })
    });

    if (!response.ok) {
        throw new Error('Failed to complete task');
    }
    load();
}

async function loadCompletedTasks() {
    let responseFromController = await fetch(`/tasks/completedTasks`);
    if (!responseFromController.ok) {
        throw new Error('Failed to fetch completed tasks');
    }

    let tasksObj = await responseFromController.json();
    const historyTableBody = document.querySelector('.history-table tbody');
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
