/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import util.Database;
import entity.Entity;

public interface Repository<E extends Entity> {

    final Connection conn = Database.getConnection();

    /**
     * Get all records
     *
     * @return
     */
    public List<E> get();

    /**
     * Return record by id
     *
     * @param id
     * @return
     */
    public E get(Integer id);

    /**
     * Get all records with specific criterias
     *
     * @param values
     * @return
     */
    public List<E> get(Map<String, Object> values);

    /**
     * Get all records with like query
     *
     * @param values
     * @return
     */
    public List<E> search(Map<String, Object> values);

    /**
     * Insert record to database
     *
     * @param entity
     * @return
     */
    public Integer add(E entity);

    /**
     * Update the record
     *
     * @param entity
     * @param data
     * @return
     */
    public boolean update(E entity);

    /**
     * Delete the specific record
     *
     * @param id
     * @return
     */
    public boolean delete(int id);
}
