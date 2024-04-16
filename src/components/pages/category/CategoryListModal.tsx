'use client'
import Link from 'next/link'
import { useEffect, useState } from 'react'
import Image from 'next/image'
import { useSearchParams } from 'next/navigation'
import constraints from '@/actions/constraints'
interface categoryMiddle {
    middleId: number
    middleName: string
}
interface category {
    largeId: number
    largeName: string
}
export default function CategoryListModal() {
    const [Lcategory, setLCategory] = useState<category[]>([] as category[])
    const [category, setCategory] = useState<categoryMiddle[]>([] as categoryMiddle[])
    const searchParams = useSearchParams()
    const largeId = searchParams.get('largeId')
    const middleId = searchParams.get('middleId')
    useEffect(() => {
        const getCategory = async () => {
            const data1 = await fetch(`${constraints.Server_Url}/api/v1/category/`)
            if (data1) {
                const response = await data1.json()
                setLCategory(response)
            }
        }
        getCategory()
    }, [])

    useEffect(() => {
        const getCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/middle/${largeId}`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }
        getCategory()
    }, [largeId])

    return (
        <div>
            <div className="absolute bg-gray-700 bg-opacity-70 top-20 left-0 w-full h-dvh z-[1399]"></div>
            <div className="absolute top-12 left-0 w-full z-[1400] bg-white max-h-[(-45px) + 100vh] overflow-y-scroll">
                <div className="grid grid-cols-2">
                    <ul>
                        {Lcategory &&
                            Lcategory.map((item: category, index) => (
                                <li key={index}>
                                    <button className="w-full h-[42px] overflow-hidden overflow-ellipsis text-xs pl-4 text-start border-b-[1px]">
                                        {item.largeName}
                                    </button>
                                </li>
                            ))}
                    </ul>
                    <div className="bg-gray-100 flex-grow-0 flex-shrink-0 basis-1/2">
                        <ul className="bg-gray-100 min-h-full absolute top-0 w-1/2">
                            <li className="flex flex-1 m-w-full overflow-hidden text-ellipsis whitespace-nowrap text-xs pl-[15px] items-center h-[42px]">
                                <Link href={'#'}>
                                    <button className="flex items-center w-full h-[42px] overflow-hidden overflow-ellipsis text-xs text-start border-b-[1px]">
                                        전체보기
                                        <div className="w-4 h-4 relative  ">
                                            <Image
                                                src="https://img.icons8.com/sf-ultralight/25/000000/back.png"
                                                alt="back"
                                                style={{ transform: 'rotate(180deg)' }}
                                                fill
                                            />
                                        </div>
                                    </button>
                                </Link>
                            </li>

                            {category &&
                                category.map((item: categoryMiddle, idx) => (
                                    <li key={idx}>
                                        <Link href={`/productlist?largeId=${largeId}&middleId=${middleId}`}>
                                            <button className="w-full h-[42px] overflow-hidden overflow-ellipsis text-xs pl-4 text-start border-b-[1px]">
                                                {item.middleName}
                                            </button>
                                        </Link>
                                    </li>
                                ))}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
