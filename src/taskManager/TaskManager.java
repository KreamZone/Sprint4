package taskManager;

import java.util.*;

import Task.*;
import TaskStatus.TaskStatus;

public class TaskManager {
    private int ID = 0;
    public Map<Integer, Subtask> subtask = new HashMap<>();
    public Map<Integer, Task> task = new HashMap<>();
    public Map<Integer , Epic> epic = new HashMap<>();

    public Map<Integer, Subtask> getSubtask() {
        return subtask;
    }

    public Map<Integer, Task> getTask() {
        return task;
    }

    public Map<Integer, Epic> getEpic() {
        return epic;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Subtask> getAllSubtasks () {
        List<Subtask> subtasks = new ArrayList<>();
        for(Subtask newSubtask : subtask.values()){
            subtasks.add(newSubtask);
        }
        return  subtasks;
    }

    public List<Task> getAllTasks () {
        return new ArrayList<>(task.values());
    }

    public List<Epic> getAllEpics () {
        return new ArrayList<>(epic.values());
    }

    public void addNewTask(Task newTask){
        newTask.setTaskID(ID);
        task.put(newTask.getTaskID(), newTask);
        ID++;
    }

    public void addNewSubtask(Subtask newSubtask, Epic epic) {
        newSubtask.setTaskID(ID);
        subtask.put(newSubtask.getTaskID(), newSubtask);
        ID++;
        epic.addNewSubtask(newSubtask);
    }

    public void addNewEpic(Epic newEpic) {
        newEpic.setTaskID(ID);
        epic.put(newEpic.getTaskID(), newEpic);
        ID++;
    }

    public Task updateTask(String newTaskName, String newTaskDescription, Task task){
        task.setTaskName(newTaskName);
        task.setTaskDescription(newTaskDescription);
        task.setTaskStatus(TaskStatus.IN_PROCESS);
        return task;
    }

    public Epic updateEpic(String newTaskName, String newTaskDescription, Epic epic){
        epic.setTaskName(newTaskName);
        epic.setTaskDescription(newTaskDescription);
        epic.setTaskStatus(TaskStatus.IN_PROCESS);
        return epic;
    }

    public Task updateSubtask(String newTaskName, String newTaskDescription, Subtask subtask){
        subtask.setTaskName(newTaskName);
        subtask.setTaskDescription(newTaskDescription);
        subtask.setTaskStatus(TaskStatus.IN_PROCESS);
        return subtask;
    }

    public Task getTaskByID(int id){
        return task.get(id);
    }

    public Subtask getSubtaskByID(int id){
        return subtask.get(id);
    }

    public Epic getEpicByID(int id){
        return epic.get(id);
    }


    public Subtask deleteSubtaskByID(int id){
        Subtask newSubtask = subtask.remove(id);
        newSubtask.getMasterTask().removeSubtask(newSubtask);
        return newSubtask;
    }

    public void deleteEpicByID(int id) {
        epic.remove(getEpicByID(id).getTaskID());
    }

    public void deleteTaskByID(int id) {
        task.remove(getTaskByID(id).getTaskID());
    }

    public List<Subtask> getSubtaskByEpic (Epic epic){
        return epic.getSubtasks();
    }

    public void clearAllSubtasks(){
        subtask.clear();
        for (Epic newEpic : epic.values()){
            newEpic.removeAllSubtasks();
        }
    }

    public void clearAllTasks(){
        task.clear();
    }

    public void clearAllEpics() {
        clearAllSubtasks();
        epic.clear();
    }
}
