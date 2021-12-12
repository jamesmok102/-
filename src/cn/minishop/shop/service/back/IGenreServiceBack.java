package cn.minishop.shop.service.back;

import cn.minishop.shop.vo.Genre;

import java.util.List;
import java.util.Set;

public interface IGenreServiceBack {
    /**
     * 增加商品分类
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean insert(Genre vo) throws Exception;

    /**
     * 更新商品分类
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean update(Genre vo) throws Exception;

    /**
     * 删除商品分类
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean delete(Set<Integer> ids) throws Exception;

    /**
     * 返回商品分类列表
     * @return
     * @throws Exception
     */
    public List<Genre> list() throws Exception;
}
