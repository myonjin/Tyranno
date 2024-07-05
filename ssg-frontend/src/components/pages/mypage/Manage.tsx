import Link from 'next/link'
function Manage() {
    const ManageList = [
        {
            id: 1,
            title: '나의 주문관리',
            items: [
                { name: '주문/배송조회', src: '/mypage/manage/orderlist' },
                { name: '구매 내역', src: '/mypage/manage/orderlist' },
                { name: '항공권 예약조회', src: '/ready' },
                { name: '호텔 예약조회', src: '/ready' },
                { name: '선물함', src: '/ready' },
                { name: '자주구매 상품', src: '/ready' },
                { name: '정기배송 설정 관리', src: '/ready' },
            ],
        },

        {
            id: 2,
            title: '나의 활동관리',

            items: [
                { name: '좋아요', src: '/ready' },
                { name: '상품리뷰', src: '/ready' },
                { name: '새벽배송 알비백 관리', src: '/ready' },
                { name: '이벤트 참여현황', src: '/ready' },
                { name: 'e-mail 답변확인', src: '/ready' },
                { name: '상품 Q&A', src: '/ready' },
                { name: '입고알림내역', src: '/ready' },
                { name: '우르르 앵콜내역', src: '/ready' },
                { name: '행사알림내역', src: '/ready' },
            ],
        },
        {
            id: 3,
            title: '나의 정보관리',
            items: [
                { name: '회원정보 변경', src: '/mypage/manage/myinfochange' },
                { name: '비밀번호 변경', src: '/mypage/manage/passwordchange' },
                { name: '배송지 관리', src: '/mypage/manage/addresschange' },
                { name: '맞춤정보 관리', src: '/ready' },
                { name: '마켓팅 수신 동의', src: '/mypage/manage/ssgagree' },
                { name: '개인정보 제3자 제공 동의', src: '/mypage/manage/pointagree' },
                { name: '제휴 멤버십 관리', src: '/ready' },
                { name: '로그인 정보 관리', src: '/ready' },
                { name: 'SNS 연결 설정', src: '/ready' },
                { name: '회원탈퇴', src: '/mypage/manage/removeuser' },
                { name: '개인정보 관리', src: '/mypage/manage/myinfochange' },
            ],
        },
    ]
    return (
        <section className="p-4">
            {ManageList.map((list, index) => (
                <div key={list.id} className="mt-4">
                    <h3 className="font-bold mb-3 text-xl ">{list.title}</h3>
                    <ul className="grid grid-cols-2 gap-2 text-gray-500 mb-4">
                        {list.items?.map((item, idx) => (
                            <li key={idx}>
                                <Link href={item.src} className="text-lg">
                                    {item.name}
                                </Link>
                            </li>
                        ))}
                    </ul>
                    {index !== ManageList.length - 1 && <div className=" border"></div>}
                </div>
            ))}
        </section>
    )
}
export default Manage
