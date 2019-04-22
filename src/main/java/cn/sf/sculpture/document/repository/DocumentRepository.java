package cn.sf.sculpture.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.document.domain.entity.Document;



/**
 * @author ztw
 * @date 2018/12/29
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByObjectId(Long objectId);
}
