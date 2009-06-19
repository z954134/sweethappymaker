package shm.dao;

import javax.jdo.PersistenceManager;

import shm.model.Member;
import shm.model.MemberMeta;

public class MemberDao extends GenericDao<Member> {

    private MemberMeta m = new MemberMeta();

    public MemberDao(PersistenceManager pm) {
        super(pm, Member.class);
    }

    /**
     * メンバーIDからメンバーを取得する。
     * 
     * @param memberId
     *            メンバーID
     * @return メンバー
     */
    public Member getMemberByMemberId(String memberId) {

        Member member =
            select()
                .from(Member.class)
                .where(m.memberId.eq(memberId))
                .getSingleResult();
        return member;
    }

    /**
     * 指定されたメンバーIDが存在するか判定する。
     * 
     * @param memberId
     *            メンバーID
     * @return 判定結果
     */
    public boolean exists(String memberId) {
        Member m = getMemberByMemberId(memberId);
        return m != null;
    }
}
