package sn.isi.l3gl.core.taskcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.l3gl.core.taskcore.domain.Task;
import sn.isi.l3gl.core.taskcore.domain.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
    long countByStatus(TaskStatus status);
}
