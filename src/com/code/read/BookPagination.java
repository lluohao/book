package com.code.read;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.code.util.StreamUtils;

/**
 * �鼮��ҳ��
 * 
 * @author LostHistory
 */
public class BookPagination {
	/**
	 * ��ǵ�ǰԤ���ص�����ҳ�ţ����Ϊ-1��ʾ�Ѿ�������
	 */
	private int loadIndex;
	/**
	 * �Ȿ������ҳ�ţ����Ϊ0��ʾû�м������
	 */
	private int maxPage;
	/**
	 * ��ҳ����
	 */
	private BookDate book;
	/**
	 * �����ȡ�õ�ҳ�����
	 */
	private Map<Integer, Page> pages;
	/**
	 * ���ÿ���ж��ٸ���
	 */
	private int rows;
	/**
	 * ���ÿҳ�ж�����
	 */
	private int cols;

	public BookPagination(BookDate book, int rows, int cols) throws IOException {
		this.pages = new HashMap<Integer,Page>();
		this.book = book;
		this.rows = rows;
		this.cols = cols;
		initPage(5);// �ȼ���20ҳ
	}

	public int getMaxPage() {
		if (maxPage == 0) {
			do {
				try {
					initPage(loadIndex * 2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (maxPage == 0);
		}
		return maxPage;
	}

	private void initPage(int toPageNum) throws IOException {
		if (loadIndex == -1) {// ����Ѿ�������ϣ�ֱ�ӷ���
			return;
		}
		if (loadIndex >= toPageNum) {// ����Ѿ����ص���ҪԤ���ص�ҳ�棬ֱ�ӷ���
			return;
		}
		InputStream in = book.getData();// ��ȡ��������ת��Ϊ�ַ���
		InputStreamReader isr = new InputStreamReader(in,"GB2312");
		for (int i = loadIndex + 1; i <= toPageNum; i++) {// �ӵ�ǰ�Ѿ����ص���һҳֱ������ҪҪ���ص�ҳ��
			Page page = new Page();
			for (int j = 0; j < cols; j++) {// ÿһҳ��cols��
				String line = StreamUtils.readLine(isr, 2 * rows);// һ������ռ����λ�����Ҫ��2
				if (line == null) {// ������鶼�Ѿ����꣬����loadIndexΪ-1������
					loadIndex = -1;// ����Ѿ��������
					pages.put(i, page);// �����һҳҲ���������
					this.maxPage = i;// �������ҳ��
					return;
				}
				page.put(line);
			}
			this.pages.put(i, page);
		}
		loadIndex = toPageNum;
	}

	public BookDate getBook() {
		return book;
	}

	public Map<Integer, Page> getPages() {
		return pages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * ��ȡָ��ҳ�ŵ���ҳ����
	 * 
	 * @param index
	 * @return
	 */
	public Page getPage(int index) {
		if (index <= 0 || (loadIndex == -1 && index > maxPage)) {// ��������ҳ��С��0���ߴ������ҳ�ţ����ؿ�
			return null;
		}
		if (index > loadIndex && loadIndex > 0) {// ���û��Ԥ���ص���һҳ���ȼ���
			try {
				initPage(index + 20);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pages.get(index);
	}

}
