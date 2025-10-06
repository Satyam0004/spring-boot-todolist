const API_URL = "http://localhost:8080/tasks";

const taskList = document.getElementById("task-list");
const taskTitleInput = document.getElementById("task-title");
const addBtn = document.getElementById("add-btn");

// Fetch all tasks and display
async function fetchTasks() {
    taskList.innerHTML = "";
    const res = await fetch(API_URL);
    const tasks = await res.json();
    tasks.forEach(renderTask);
}

// Render a single task
function renderTask(task) {
    const li = document.createElement("li");
    li.textContent = task.title;
    li.className = task.status === "COMPLETED" ? "completed" : "";

    // Complete button
    const completeBtn = document.createElement("button");
    completeBtn.textContent = "✔";
    completeBtn.onclick = () => markCompleted(task.id);

    // Delete button
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "🗑";
    deleteBtn.onclick = () => deleteTask(task.id);

    li.appendChild(completeBtn);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
}

// Add new task
addBtn.addEventListener("click", async () => {
    const title = taskTitleInput.value.trim();
    if (!title) return alert("Enter a task title");

    const res = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, status: "PENDING" })
    });

    const task = await res.json();
    renderTask(task);
    taskTitleInput.value = "";
});

// Delete task
async function deleteTask(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    fetchTasks();
}

// Mark as completed
async function markCompleted(id) {
    const res = await fetch(`${API_URL}/${id}/complete`, { method: "PUT" });
    await res.json();
    fetchTasks();
}

// Initial load
fetchTasks();
