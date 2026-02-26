package sn.isi.l3gl.core.taskcore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.l3gl.core.taskcore.domain.Task;
import sn.isi.l3gl.core.taskcore.domain.TaskStatus;
import sn.isi.l3gl.core.taskcore.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createTask(String title, String description) {
        Task task = new Task(title, description, TaskStatus.TODO);
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task updateStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId));

        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public long countCompletedTasks() {
        return taskRepository.countByStatus(TaskStatus.DONE);
    }
}
