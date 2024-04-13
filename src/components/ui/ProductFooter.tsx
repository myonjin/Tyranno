'use client'
import Image from 'next/image'
import HeartIcon from '@/images/HeartIcon.png'
import RedHeartIcon from '@/images/RedHeartIcon.png'
import { useEffect, useState } from 'react'
import ProductOptions from '../pages/product/ProductOptions'
import { useParams } from 'next/navigation'
import { ProductDataType } from '@/types/ProductDetailDataType'
import { LIKEType } from '@/types/LikeType'
import { LikeClickAPI, getLikeAPI } from '@/actions/product'
import { useRouter } from 'next/navigation'

function ProductFooter({ data }: { data: ProductDataType }) {
    const params = useParams<{ productId: string }>()
    const [isModal, setIsModal] = useState<boolean>(false)
    const [like, setLike] = useState<number>(99)

    const handleLike = async () => {
        const body: LIKEType = {
            productId: params.productId,
        }
        const res = await LikeClickAPI(body)
        const response = await getLikeAPI(params.productId)
        setLike(response.result)
    }
    useEffect(() => {
        const getLike = async () => {
            const response = await getLikeAPI(params.productId)
            setLike(response.result)
        }
        getLike()
    }, [params.productId])
    const router = useRouter()
    const handleNonUser = async () => {
        alert('로그인 후 이용해주세요.')
        router.push(`/user/login`)
    }
    // console.log(like)
    return (
        <section>
            <div className="fixed bottom-0 w-screen z-[10] ">
                <ul className="flex flex-grow-0 items-center h-12 w-full ">
                    {like === 99 ? (
                        <button
                            onClick={() => handleLike()}
                            className=" flex justify-center items-center w-14 bg-white h-12"
                        >
                            <Image src={HeartIcon} alt="안좋아요"></Image>
                        </button>
                    ) : like === 11 ? (
                        <button
                            onClick={() => handleLike()}
                            className=" flex justify-center items-center w-14 bg-white h-12"
                        >
                            <Image src={RedHeartIcon} alt="좋아요" />
                        </button>
                    ) : like === null ? (
                        <button
                            onClick={() => handleNonUser()}
                            className=" flex justify-center items-center w-14 bg-white h-12"
                        >
                            <Image src={HeartIcon} alt="안좋아요"></Image>
                        </button>
                    ) : (
                        ''
                    )}
                    <button className="flex justify-center items-center font-semibold w-28  h-12 bg-white">
                        <Image
                            width="30"
                            height="30"
                            src="https://img.icons8.com/windows/32/packaging.png"
                            alt="packaging"
                        />
                        선물하기
                    </button>
                    <button
                        className="flex justify-center items-center font-semibold text-white bg-red-500 flex-grow h-12"
                        onClick={() => setIsModal(true)}
                    >
                        구매하기
                    </button>
                </ul>
            </div>

            <ProductOptions isModal={isModal} setIsModal={setIsModal} productId={params.productId} productData={data} />
        </section>
    )
}
export default ProductFooter
