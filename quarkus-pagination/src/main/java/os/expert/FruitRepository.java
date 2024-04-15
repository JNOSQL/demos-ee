package os.expert;

import jakarta.data.Sort;
import jakarta.data.page.CursoredPage;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.Find;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Repository;
import org.eclipse.jnosql.mapping.NoSQLRepository;

@Repository
public interface FruitRepository extends NoSQLRepository<Fruit, String> {

    @Find
    CursoredPage<Fruit> cursor(PageRequest pageRequest, Sort<Fruit> order);

    @Find
    @OrderBy("name")
    Page<Fruit> offSet(PageRequest pageRequest);
}
