import RemoveUser from '@/components/pages/mypage/removeUser'
export default function removeUser() {
    return (
        <section className="px-3 bg-white">
            <div className="justify-between flex mb-1" style={{ borderBottom: '1px solid #c6e6e6' }}>
                <h3 className=" py-4 text-base font-bold">탈퇴 유의사항</h3>
            </div>
            <div className="m-2">
                <h3 className="font-bold mt-2">SSG MONEY 소멸</h3>
                <div style={{ color: '#666', fontSize: '13px' }}>
                    -회원탈퇴 시 보유하고 계신 SSG MONEY가 자동 소멸되며 복원되지 않습니다.
                    <br /> -출금 가능 금액이 있으신 경우 고객센터(1577-3419)로 출금 신청 후 탈퇴해주세요.
                    <br />
                    <p className="text-[#ff5452]">
                        -SSGPAY서비스 이용 회원은 SSG.COM회원탈퇴를 하시더라도 이마트, 신세계백화점, 스타벅스 등
                        SSGPAY가맹점에서 SSG MONEY를 계속 사용하실 수 있습니다.
                    </p>
                </div>
                <h3 className="font-bold mt-4">회원 재가입 제한</h3>
                <div style={{ color: '#666', fontSize: '13px' }}>
                    -회원탈퇴 후 재가입 시 신규회원으로 가입되며, 탈퇴 전의 회원정보, 주문정보, 마일리지, 쿠폰은
                    복원되지 않습니다.
                    <br /> -사이트 정책에 따라 최대 30일 동안 회원 재가입이 불가능합니다.
                </div>
                <h3 className="font-bold mt-4">개인정보 파기</h3>
                <div style={{ color: '#666', fontSize: '13px' }}>
                    -개인정보는 회원 재가입 유예기간 경과 후 즉시 파기됩니다.
                    <br /> -단, 전자상거래법, 통신비밀보호법 등 관련 법령의 규정에 의하여 아래의 개인정보가 일정기간
                    보관됩니다.
                </div>
                <h3 className="font-bold mt-4">통합 ID 서비스</h3>
                <div style={{ color: '#666', fontSize: '13px' }}>
                    -회원탈퇴 시 SSG.COM 회원탈퇴가 되며, 가입되어 있는 신세계포인트 통합 ID로 SSG.COM을 제외한
                    신세계포인트닷컴 및 그룹사 사이트를 계속 이용하실 수 있습니다.
                    <br /> -아이디 변경을 원하시는 경우, 신세계포인트 통합 ID 서비스 탈퇴 후 재가입을 통해 새로운
                    아이디로 가입하실 수 있습니다.
                </div>
            </div>
            <div className="mb-20">
                <RemoveUser />
            </div>
        </section>
    )
}
