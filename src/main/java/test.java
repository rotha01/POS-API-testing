public class test {
//    public List<Employee> findEmployeesDynamically(String department, Double minSalary) {
//        // 1. បង្កើត CriteriaBuilder (រោងចក្របង្កើត Query)
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        // 2. បង្កើត CriteriaQuery ដោយកំណត់ប្រភេទលទ្ធផលដែលចង់បាន (Employee)
//        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
//
//        // 3. កំណត់ឃ្លា FROM (ចាប់ផ្តើមពីតារាង Employee)
//        Root<Employee> employee = cq.from(Employee.class);
//
//        // 4. កំណត់ឃ្លា SELECT (ស្មើនឹង SELECT * FROM Employee)
//        cq.select(employee);
//
//        // 5. បង្កើតតារាងប្រមូល Predicates (លក្ខខណ្ឌ WHERE) បែបឌីណាមិក
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (department != null && !department.isEmpty()) {
//            // ប្រសិនបើមានបញ្ចូល department -> WHERE employee.department = :department
//            predicates.add(cb.equal(employee.get("department"), department));
//        }
//
//        if (minSalary != null) {
//            // ប្រសិនបើមានបញ្ចូលប្រាក់ខែអប្បបរមា -> WHERE employee.salary >= :minSalary
//            predicates.add(cb.greaterThanOrEqualTo(employee.get("salary"), minSalary));
//        }
//
//        // 6. ដាក់លក្ខខណ្ឌចូលទៅក្នុង Query ប្រសិនបើមានលក្ខខណ្ឌត្រូវបានបញ្ចូល
//        if (!predicates.isEmpty()) {
//            cq.where(cb.and(predicates.toArray(new Predicate[0])));
//        }
//
//        // 7. ប្រតិបត្តិការ Query និងទាញយកលទ្ធផល
//        TypedQuery<Employee> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }


}
