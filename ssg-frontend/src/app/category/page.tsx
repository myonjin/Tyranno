'use client'

import { GetCategoryAPI } from '@/actions/category'
import constraints from '@/actions/constraints'
import CategoryModal from '@/components/pages/category/CategoryModal'
import Image from 'next/image'
import Link from 'next/link'
import { useParams } from 'next/navigation'
import { useEffect, useState } from 'react'

export interface CategoryType {
    largeId: string
    largeName: string
    largeImageUrl: string
}

export default function CategoryPage() {
    const [selectedLCategory, setSelectedLCategory] = useState<string>('')
    const [category, setCategory] = useState<CategoryType[]>([] as CategoryType[])

    useEffect(() => {
        const GetCategory = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/`)
            if (data) {
                const response = await data.json()
                setCategory(response)
            }
        }

        GetCategory()
    }, [])

    useEffect(() => {
        setIsOpen(Array(category.length).fill(false))
    }, [category])

    const [isOpen, setIsOpen] = useState<Boolean[]>(Array(category.length).fill(false))

    const handleOpen = (e: React.MouseEvent<HTMLLIElement>) => {
        const role = e.currentTarget.getAttribute('role')
        if (role) {
            const index = parseInt(role)
            if (!isOpen[index]) {
                setIsOpen((prev) => {
                    return prev.map((item, idx) => {
                        return index === idx ? !item : false
                    })
                })
            }
        }
    }

    return (
        <div>
            <div className="h-screen">
                <div className="pt-[15px] pr-[10px] pb-[25px] pl-[10px]">
                    <p className="font-bold">전체 카테고리</p>
                    {category
                        .reduce((acc: CategoryType[][], item, index) => {
                            const groupIndex = Math.floor(index / 5)
                            if (!acc[groupIndex]) {
                                acc[groupIndex] = []
                            }
                            acc[groupIndex].push(item)
                            return acc
                        }, [])
                        .map((group, groupIndex) => (
                            <div className="py-2 min-h-[80px]" key={groupIndex}>
                                <GroupNav
                                    group={group}
                                    gx={groupIndex}
                                    handleOpen={(e) => {
                                        handleOpen(e)
                                        setSelectedLCategory(e.currentTarget.getAttribute('value') ?? '')
                                    }}
                                    selectedLCategory={selectedLCategory}
                                    isOpen={isOpen[groupIndex]}
                                />
                            </div>
                        ))}
                </div>
            </div>
        </div>
    )
}

const GroupNav = ({
    group,
    gx,
    handleOpen,
    isOpen,
    selectedLCategory,
}: {
    group: CategoryType[]
    gx: number
    handleOpen: React.MouseEventHandler<HTMLLIElement>

    isOpen: Boolean
    selectedLCategory: string
}) => {
    return (
        <div className="relative left-0 overflow-hidden w-full ">
            <ul className={isOpen ? 'grid grid-cols-5 h-full relative' : 'grid grid-cols-5 relative'}>
                {group.map((item) => (
                    <NavItem key={item.largeId} item={item} value={item.largeId} handleOpen={handleOpen} gx={gx} />
                ))}
            </ul>
            {isOpen && (
                <ul className="text-xs  px-3 py-3 bg-gray-100 my-[5px] box-border">
                    <CategoryModal largeId={selectedLCategory} />
                </ul>
            )}
        </div>
    )
}
const NavItem = ({
    item,
    value,
    handleOpen,
    gx,
}: {
    item: CategoryType
    value: CategoryType['largeId']
    handleOpen: React.MouseEventHandler<HTMLLIElement>
    gx: number
}) => {
    return (
        <li
            className="relative flex flex-col justify-center items-center"
            onClick={handleOpen}
            role={gx.toString()}
            value={value}
        >
            <Image src={item.largeImageUrl} alt={item.largeName} width={64} height={64} priority={true} />
            <p className="text-[10px] text-gray-500">{item.largeName}</p>
        </li>
    )
}
