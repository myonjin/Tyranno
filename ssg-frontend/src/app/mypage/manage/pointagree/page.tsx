import PointAgree from '@/components/pages/mypage/PointAgree'
import SsgAgree from '@/images/SsgAgree.png'
import Image from 'next/image'
export default function page() {
    return (
        <main>
            <div style={{ backgroundColor: '#f8f8f8', padding: '15px 20px' }}>
                <h3> 신세계포인트 옴니서비스</h3>
            </div>

            <div className="bg-[#f8f8f8] m-4">
                <Image src={SsgAgree} alt="pointagree" width={200} height={200} />
            </div>
            <hr className="h-2" />
            <PointAgree />
        </main>
    )
}
