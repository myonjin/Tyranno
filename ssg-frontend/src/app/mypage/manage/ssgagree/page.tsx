import SsgAgreeChange from '@/components/pages/mypage/SsgAgreeChange'
import SsgAgreeInfo from '@/components/pages/mypage/SsgAgreeInfo'

export default function page() {
    return (
        <main>
            <SsgAgreeInfo />
            <SsgAgreeChange />
            <div style={{ backgroundColor: '#f8f8f8', padding: '15px 20px' }}>
                <h3>마케팅 정보 수신 안내</h3>
            </div>
            <div className="text-sm p-5 ml-3 ">
                - 주문/배송 안내, 회사의 주요 정책 변경 사항 등 고객 고지 내용은 광고 정보 수신 동의 여부와 관계없이
                발송됩니다.
                <br />
                - 광고 정보 수신 동의 거부 후 약 일주일 동안 이미 예약된 광고 정보 문자 또는 이메일이 발송될 수
                있습니다.
                <br />- 앱푸쉬 메시지는 사용하시는 앱의 광고성 정보(PUSH) 받기 설정 여부에 따라 발송됩니다.
                <br /> [수신 거부 : 앱 {'>'} 설정 {'>'} 알림설정]
                <br />
                - 카카오 플러스톡 광고는 카카오 플러스톡 친구 추가되어있는 경우 발송됩니다.
                <br /> [수신거부 : 카카오톡 {'>'} SSG.COM 채널 {'>'} 우측 상단 'Ch' 아이콘 {'>'} 채널 차단
            </div>
        </main>
    )
}
